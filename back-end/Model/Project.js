const mongoose = require ('mongoose');
const User=require('./User');

let Schema = mongoose.Schema,
ObjectId = Schema.ObjectId;
const PostSchema= mongoose.Schema({
    _idUser : ObjectId,
    name: String,
    client: [ObjectId],
    title:String,
    description:String,
    deadline: Date,
    done:Boolean,
    delete: Boolean
})
module.exports = mongoose.model ('Projects', PostSchema)
