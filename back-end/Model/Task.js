const mongoose = require ('mongoose');

const PostSchema= mongoose.Schema({
    _idProject:ObjectId,
    text:String,
    deadline: Date,
    done: Boolean,
    delete: Boolean
})
module.exports = mongoose.model ('Tasks', PostSchema)