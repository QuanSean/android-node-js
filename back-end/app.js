const express = require("express");
const app = express();
const mongoose = require("mongoose");
const bodyParser = require("body-parser");

require("dotenv/config");

//Routes
app.use (bodyParser.urlencoded());
app.use (bodyParser.json())

const postRoute = require ('./routes/user');
app.use ("/user", postRoute);

const projectRoute = require ('./routes/project');
app.use ("/project", projectRoute);


app.use(bodyParser.json());



//Connect Db

// DB_CONNECTION=mongodb+srv://quansean:150598bd!@tesa-e6rwo.mongodb.net/test?retryWrites=true&w=majority

mongoose.connect(
  process.env.DB_CONNECTION,
  { useNewUrlParser: true },
  (err, success) => {
    if (err) {
      console.log(err);
    } else {
      console.log("Connected mongodb huy");
    }
  }
);

app.listen(process.env.PORT || 2409,(err, success)=>{
    if (err)
    {
        console.log (err)
    }
    else 
    {
        console.log ("Listen sss")
    }
    

});




