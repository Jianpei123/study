# study


user/getChildrenType?parentTypeId=   参数parentTypeId   获取父亲类别下的子类别，如果需要单独获取一级分类把参数设为1即可
when parentTypeId=1,the server will response:
[{"name":"前端开发","id":5},{"name":"后端开发","id":7},{"name":"软件测试","id":12},{"name":"计算机技能","id":15},{"name":"计算机网络","id":17},{"name":"数据库","id":19}]


user/getCourseByType?typeId=         参数typeId   获取二级分类下的课程

user/getCourseById?courseId=         参数courseId   获取课程详情

user/getCourseByName?name=           参数name     根据名字搜索课程【用于搜索模块】