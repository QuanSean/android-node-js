const mongoose = require ('mongoose');
let Schema = mongoose.Schema,
ObjectId = Schema.ObjectId;
const PostSchema= mongoose.Schema({
    _id:Number,
    title:String,
    deadline: Date,
    done: Boolean,
    delete: Boolean
})
module.exports = mongoose.model ('Tasks', PostSchema)