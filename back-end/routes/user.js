const express = require('express');
const router = express.Router();
const user = require ('../Controllers/User');
const Project= require('../Model/Project');




router.post("/register", (req, res, next) => {
    console.log(req.body.password);
    if (!req.body.email || !req.body.password) {
      res.status(400).send({
        success: false
      })
    } else {
      user.register(req.body.email, req.body.password, req.body.fullname, (error, result) => {
        if (error) {
          res.status(400).send({
            success: false,
            detail: error
          });
        } else {
          res.status(201).send({
            success: true
          })
        }
      })
    }
  });

router.post("/sss",async (req, res)=>{
    const post=new User ({
        email:req.body.email,
        password: req.body.password,
        fullname: req.body.fullname,
        delete: false
    });
    try{
        const savedPost=await post.save();
        res.json(savedPost);
    }
    catch(err){
        res.send(savedPost);
    }
});


router.post("/login", (req, res, next) => {
  user.login(req.body.email, req.body.password, (error, result) => {
    if (error || !result) {
      res.status(401).send({
        result: false
      });
    } else {
      // user.updateToken(req.body.email, req.body.password, result);
      delete result.user.password;
      res.status(200).send({
        result: true,
        token: result.token,
        info: result.user
      });
    }
  })
})





module.exports=router;
