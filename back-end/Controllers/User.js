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
let user={
    register: async (email, password, fullname, callback) => {
        email = email.toLowerCase();
        let isEmailUseable = await isExistEmail(email);
        if (!isEmailUseable) {
          err = 'email has been used';
          return callback(err, null);
        } else {
          password = crypto.createHash('sha256').update(password).digest('hex');
          let newUser = new User({
            email: email,
            fullname: fullname,
            password: password,
            deleted: false
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
    }
}
module.exports=user