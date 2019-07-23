const mongoose = require ('mongoose');

const PostSchema = mongoose.Schema({
    email:String,
    password: String,
    fullname: String,
    delete: Boolean
});

module.exports=mongoose.model ('User',PostSchema);