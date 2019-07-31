const express = require('express');
const router = express.Router();
const User = require ('../Model/User');
const Project= require('../Model/Project');
const Utility= require ('../Common/utility');
let crypto = require("crypto");

let isExistEmail = (email) => {
    return new Promise((resolve, reject) => {
      User.find({
        "email": email,
        "deleted": false
      }, (err, res) => {
        if (err) {
          resolve(false)
        }
        if (res.length == 0) {
          resolve(true);
        } else
          resolve(false);
      });
    })
  }

  let user = {
    register: async (email, password, name, callback) => {
      email = email.toLowerCase();
      let isEmailUseable = await isExistEmail(email);
      if (!isEmailUseable) {
        return callback(new Error(ERROR.DUPLICATE), null);
      } else {
        password = crypto.createHash('sha256').update(password).digest('hex');
        let newUser = new User({
          email: email,
          name: name,
          password: password,
          deleted: false,
          token:''
        });
        newUser.save()
          .then(res => callback(null, res))
          .catch(err => callback(err, null));
      }
    },
    login: async (email, password, callback) => {
      password = crypto.createHash('sha256').update(password).digest('hex');
      email = email.toLowerCase();
      User.findOne({
        email: email,
        password: password,
        deleted: false
      }, (err, res) => {
        if (res != null) {
          let token = Utility.getToken(res.email);
          if (token) {
            return callback(null, { user: { ...res._doc }, token: token[0] });
          } else {
            Utility.computingJWT(email, (err, newToken) => {
              Utility.addNewTokenForUser(email, newToken);
              return callback(null, { user: { ...res._doc }, token: newToken });
            })
          }
        } else {
          callback(err, null);
        }
      })
    },
    logout: async (token, callback) => {
      User.findOneAndUpdate({
        "token": token
      }, { "token": "" }, callback);
    },
    getBaseInfo: async (id, callback) => {
      let user = await User.findOne({
        _id: id
      }).select("name", "dob", "gender", "organization", "avatar_path");
      if (user) {
        return callback(null, user);
      } else {
        return callback(new Error("NOT_FOUND"), null);
      }
    },
    getBaseInfoOfAmoutUsers: async (amount, callback) => {
      let users;
      if (amount > 0) {
        User.find({})
          .limit(amount)
          .select("name", "dob", "gender", "organization", "avatar_path")
          .exec(callback);
      } else {
        User.find({})
          .select("name", "dob", "gender", "organization", "avatar_path")
          .exec(callback);
      }
    },
    updateInfo: (user, callback) => {
      user.password = crypto.createHash('sha256').update(user.password).digest('hex');
      User.findById(user._id, (err, oldUser) => {
        oldUser.name = user.name;
        oldUser.dob = user.dob;
        oldUser.password = user.password;
        oldUser.gender = user.gender;
        oldUser.organization = user.organization;
        oldUser.phone = user.phone;
        oldUser.avatar_path = user.avatar_path;
        oldUser.last_update = Date.now();
        oldUser.save()
          .then(res => callback(null, res))
          .catch(err => callback(err, null));
      })
    },
    deleteAccount: (token, callback) => {
      User.findOne({
        "token": token
      }, (err, doc) => {
        if (doc) {
          doc.token = "";
          doc.deleted = true;
          doc.save()
            .then(res => callback(null, res))
            .catch(err => callback(err, null));
        } else {
          callback(true, null);
        }
      });
    }
  }
module.exports=user