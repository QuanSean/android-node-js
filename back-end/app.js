const express = require("express");
const app = express();
const mongoose = require("mongoose");
const bodyParser = require("body-parser");
require("dotenv/config");

//Routes
app.use(bodyParser.json());

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
  }
);

app.listen(3000);
