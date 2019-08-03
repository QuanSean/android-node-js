const express = require('express');
const router = express.Router();
const user = require ('../Controllers/User');
const Project= require('../Controllers/Project');
const Utility = require('../Common/utility.js')
let crypto = require("crypto");
const UserModel = require ('../Model/User');
router
.get("/my/:id", async (req, res, next) => {
  Utility.verifyToken(req.headers.token, (err, user) => {
    if (user) {
      // if (req.headers._id) {
      if (1==1){
        Project.getInfo(user._id, req.params.id, (err, result) => {
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
        })
      } else {
        res.status(404).json({
          result: false,
          detail: "Query error none id"
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
  .post("/task", async (req, res, next) => {
    let newTask = req.body.newTask;
    Utility.verifyToken(req.headers.token, (err, user) => {
      if (user) {
        if (
          newTask._id &&
          newTask.title&&
          newTask.deadline
           ) {
          // Cloudinary.upload(newQuestion.img, (err, url) => {
          //   newQuestion.img = url;
          Project.addTask(newTask, user._id, (err, result) => {
              if (err) {
                res.status(404).json({
                  result: false,
                  detail: "query error"
                });
              } else {
                res.status(200).json({
                  result: true,
                  detail: result.task
                });
              }
            })
          // })
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
  .get('/task',(req, res, next)=>{
    let newTask = req.body.newTask;
    Utility.verifyToken(req.headers.token, (err, user) => {
      if (user)
      {
        Project.getTask(newTask,user._id,(err, result)=>{
          if (err)
          {
            res.status(404).json({
              result: false,
              detail: "query error"
            });
          }
          else{
            res.status(200).json({
              result: true,
              detail: result.task
            });
          }
        })
      }
      else
      {
        res.status(401).send({
          result: false,
          detail: "UnAuthorized"
        });
      }
    })

  })
  .delete("/task", async (req, res, next) => {
    let newTask = req.body.newTask;
    Utility.verifyToken(req.headers.token, (err, user) => {
      if (user) {
        if (
          newTask._id 
           ) {
          // Cloudinary.upload(newQuestion.img, (err, url) => {
          //   newQuestion.img = url;
          Project.deleteTask(newTask, user._id, (err, result) => {
              if (err) {
                res.status(404).json({
                  result: false,
                  detail: "query error"
                });
              } else {
                res.status(200).json({
                  result: true,
                  detail: result.task
                });
              }
            })
          // })
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
