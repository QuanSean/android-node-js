const projectModel =require ('../Model/Project');
const taskModel= require('../Model/Task')
let project={
  //PROJECT
    getListProject:(idUser,callback)=>{
      projectModel.find({_idUser:idUser},callback )
    },
    getInfo: (idUser, idProject, callback) => {
      projectModel.findOne({
          _idUser: idUser,
          _id: idProject
        }, callback)
      },
    createProject: (title,description,deadline,user, callback) => {
        let newProject = new projectModel({
            _idUser: user._id,
            title: title,
            description: description,
            done:false,
            deadline:deadline,
            task:[],
            deleted: false
        });
        
        newProject.save()
          .then(res => callback(null, res))
          .catch(err => callback(err, null))
      },
      //TASK
      addTask: (nTask, idUser, callback) => {
        projectModel.findById(nTask._id, (err, fProject) => {
          if (fProject._idUser.toString() != idUser) {
            callback(true, null);
          } 
          else {
            let newTask = new taskModel({  
              _id:fProject.task.length + 1,
              title:nTask.title,
              deadline: nTask.deadline,
              done: false,
              delete: false
            });
            
            fProject.task.push(newTask);
            fProject.save()
              .then(res => callback(null, res))
              .catch(err => callback(err, null));
          }
        })
      },
      getTask:(nTask, idUser, callback)=>{
        projectModel.findOne ({_id:nTask._id,_idUser:idUser},callback)
      },
      deleteTask:(nTask, idUser, callback)=>{
        projectModel.findById(nTask._id, (err, fProject)=>{
          if (fProject._idUser.toString() != idUser) {
            callback(true, null);
          } 
          else
          {
            let newTask = new taskModel({  
              // _id:fProject.task.length + 1,
              // title:nTask.title,
              // deadline: nTask.deadline,
              // done: false,
              delete: true
            });
            
            fProject.task.push(newTask);
            fProject.save()
              .then(res => callback(null, res))
              .catch(err => callback(err, null));
          }
        })
      }
}

module.exports = project;