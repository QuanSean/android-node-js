const projectModel =require ('../Model/Project');
let project={
    getInfo: (idUser, idProject, callback) => {
        Quest.findOne({
          _id_author: idUser,
          _id: idProject
        }, callback)
      },
    createProject: (nProject, user, callback) => {
        let newProject = new projectModel({
            _idUser: user._id,
            title: nProject.title,
            description: nProject.description,
            done:false,
            deadline:nProject.deadline,
            deleted: false
        });
        
        newProject.save()
          .then(res => callback(null, res))
          .catch(err => callback(err, null))
      }
}

module.exports = project;