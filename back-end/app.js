const express = require('express');
const app =express ();
const mongoose = require ('mongoose');
const bodyParser= require ('body-parser')
require ("dotenv/config");

//Routes
app.use (bodyParser.json());

const postRoute = require ('./routes/user');
app.use ("/user", postRoute);


//Connect Db
mongoose.connect (process.env.DB_CONNECTION,{ useNewUrlParser: true },(err, success)=>{
    if (err)
    {
        console.log (err)
    }
    else
    {
        console.log("Connected mongodb");
    }
    
});
// DB_CONNECTION=mongodb+srv://quansean:150598bd!@tesa-e6rwo.mongodb.net/test?retryWrites=true&w=majority

app.listen(3000);