# study

api说明文档

## 首页相关

<table style="text-align:center">
  <tr >
    <th width="20%">
      url
    </th>
    <th width="20%">
      参数
    </th>
    <th width="60%">
      说明
    </th>
  </tr>
  <tr>
    <td>user/getChildrenType</td>
    <td>parentTypeId</td>
    <td>获取父类别下的子类别，如果需要单独获取一级分类把参数设为1即可</td>
  </tr>
  <tr>
    <td>user/getCourseByType</td>
    <td>typeId</td>
    <td>获取二级分类下的课程</td>
  </tr>
  <tr>
    <td>user/getCourseById</td>
    <td>courseId</td>
    <td>获取课程详情</td>
  </tr>
  <tr>
    <td>user/getCourseByName</td>
    <td>name</td>
    <td>根据名字搜索课程</td>
  </tr>
</table>

## 课程详情相关
url | 参数 | 说明
--------- | ---- | -----------
user/getChaptersByCourse | courseId | 获取目录


