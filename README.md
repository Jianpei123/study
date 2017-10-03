# 在线学习平台

api说明文档

## 用户登录

url | 参数 | 说明
--------- | ---- | -----------
user/login | email,password | 后端会返回相应信息，请注意输出
user/register | email,username,password | 后端会返回相应信息，请注意输出

## 首页相关

url | 参数 | 说明
--------- | ---- | -----------
user/getChildrenType | parentTypeId | 获取父类别下的子类别，如果需要单独获取一级分类把参数设为1即可
user/getCourseByType | typeId | 获取二级分类下的课程
user/getCourseById | courseId | 获取课程详情
user/getCourseByName | name | 根据名字搜索课程

## 课程详情相关

url | 参数 | 说明
--------- | ---- | -----------
user/getChaptersByCourse | courseId | 获取目录
