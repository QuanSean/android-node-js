const express = require("express");
const app = express();
const mongoose = require("mongoose");
const bodyParser = require("body-parser");

require("dotenv/config");

//Routes
app.use (bodyParser.urlencoded());
app.use(bodyParser.json())

const postRoute = require ('./routes/user');
app.use ("/user", postRoute);

const projectRoute = require ('./routes/project');
app.use ("/project", projectRoute);


// app.use(bodyParser.urlencoded({ limit: '50mb', extended: false }));
// app.use(bodyParser.json());

// app.use(bodyparser.json());


app.use(function (req, res, next) {
  // Website you wish to allow to connect
  // Website you wish to allow to connect
    // let allowedOrigins = ['http://localhost:4200', 'http://localhost:3330', "https://xcdc.herokuapp.com/", "http://xcdc.ueuo.com/"];
    // let origin = req.headers.origin;
    // if (allowedOrigins.indexOf(origin) > -1) {
    //     res.setHeader('Access-Control-Allow-Origin', origin);
    // }
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token");
    // Request methods you wish to allow
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST');

    // Request headers you wish to allow
    // res.setHeader('Access-Control-Allow-Headers', 'X-Requested-With,content-type');

    // Set to true if you need the website to include cookies in the requests sent
    // to the API (e.g. in case you use sessions)
    res.setHeader('Access-Control-Allow-Credentials', true);

  // Pass to next layer of middleware
  next();
});


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

app.listen(3000,(err, success)=>{
    if (err)
    {
        console.log (err)
    }
    else 
    {
        console.log ("Listen sss")
    }
    

});


