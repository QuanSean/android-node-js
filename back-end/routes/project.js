const express = require('express');
const router = express.Router();
const user = require ('../Controllers/User');
const Project= require('../Controllers/Project');
const Utility = require('../Common/utility.js')
let crypto = require("crypto");

const UserModel = require ('../Model/User');


router
.post("/", async (req, res, next) => {
    let newQuest = req.body.newQuest;
    Utility.verifyToken(req.headers.token, (err, user) => {
      if (user) {
        if (newQuest.title && newQuest.description &&newQuest.deadline) {
          
            Project.createProject(newQuest, user, (err, result) => {
              if (err) {
                res.status(404).json({
                  result: false,
                  detail: "query error"
                });
              } else {
                res.status(200).json({
                  result: true,
                  detail: result
                });
              }
            // })

          })
       
        } else {
          res.status(404).json({
            result: false,
            detail: "Query error"
          });
        }
      } else {
        res.status(401).send({
          result: false,
          detail: "UnAuthorized"
        });
      }
    })
  })


module.exports=router;
