const express = require('express');
const router = express.Router();
const User = require ('../Model/User');

router.get ("/", async (req, res)=>{
    try{
        const post = await User.find();
        res.json(post);
    }
    catch (err){
        res.json ({message:err})

    }
});

router.post("/",async (req, res)=>{
    const post=new User ({
        email:req.body.email,
        password: req.body.password,
        fullname: req.body.fullname,
        delete: req.body.delete
    });
    try{
        const savedPost=await post.save();
        res.json(savedPost);
    }
    catch(err){
        res.send(savedPost);
    }
});



module.exports=router;
