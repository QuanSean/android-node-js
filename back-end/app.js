const express = require("express");
const app = express();
const mongoose = require("mongoose");
const bodyParser = require("body-parser");
require("dotenv/config");

//Routes
<<<<<<< HEAD
app.use (bodyParser.json());

const postRoute = require ('./routes/user');
app.use ("/user", postRoute);
=======
app.use(bodyParser.json());
>>>>>>> e034cf9ff92f6f29c77445602407e7703bcafedf

const postRoute = require("./routes/post");
app.use("/post", postRoute);

//Connect Db
mongoose.connect(
  process.env.DB_CONNECTION,
  { useNewUrlParser: true },
  (err, success) => {
    if (err) {
      console.log(err);
    } else {
      console.log("Connected mongodb huy");
    }
<<<<<<< HEAD
    
});
// DB_CONNECTION=mongodb+srv://quansean:150598bd!@tesa-e6rwo.mongodb.net/test?retryWrites=true&w=majority

app.listen(3000,(err, success)=>{
    if (err)
    {
        console.log (err)
    }
    else 
    {
        console.log ("Listen 3000")
    }

});
=======
  }
);

app.listen(3000);
>>>>>>> e034cf9ff92f6f29c77445602407e7703bcafedf
