const mongoose = require ('mongoose');

const PostSchema = mongoose.Schema({
    email:String,
    password: String,
    name: String,
    deleted: Boolean
});

module.exports=mongoose.model ('User',PostSchema);