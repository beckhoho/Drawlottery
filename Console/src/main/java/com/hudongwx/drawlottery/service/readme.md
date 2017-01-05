# service层约定规范

## service命名规范：

* 驼峰命名

* 动词开头:

````
    添加：add
    修改：update
    删除：delete
    查询：get
````
* 避免使用单词by...and..作为表语结构，当且仅当需要区分时才使用，不得使用其他任何命名方式

## service方法规范

### 参数

> 以下按照参数顺序说明

1. 当有分页时，分页参数全部由调用者决定，参数顺序为int currentPage,int pageSize，类型必须为int，而不能使用Integer.

1. 若当仅需要id为参数时，不提供实体参数方法

1. 参数类型只接受单一或list类型，其他类型应当由controller进行封装后传入service

### 内容

1. 无论单表多表查询，将具体操作下放至mapper接口
 
    > 不使用Example （锻炼sql编写能力）
1. 当且仅当表结构需要联动时，才使用其他service，__注意：仅做联动操作__

    > 例如添加一个评论后，在commentService进行了insertComment，
    此时projectService应当使用increaseCommentCnt(评论总数+1)
    
1. 分页封装在本层做，不再下放

1. 异常处理，通过throw ServiceException。

1. service层只能完成具体的controller与mapper的连接、多service联动两种类型操作，不得做其他多余的任何操作

1. 关联查询操作一定放在代表实体的且为被拥有关系的pojoService类中，
>例如：查询 __项目的所有报名用户__ 应当放在userService、查询 __用户收藏的项目__ 放在projectService。

1. 单表查询操作（自带属性中需要封装其他表信息）一定放在代表实体的pojoService中，
 >例如：查询 __所有的用户__ 应当放在userService中、
查询 __项目列表(需要封装状态、类型、作者信息)__ 也应当放在projectService中，
并必须将封装操作下放至dao层，由dao层进行sql语句关联封装。

1. 多对多查询操作应当放在 __关联pojoService__ 中
 >例如：查询有某标签的项目、或项目包含的标签都应当放在 tagProjectService 中

1. 一对多、一对一或多对一关系查询，应当放在 __被动关系pojoService__ 中，
 >例如：查询用户发布的所有项目的方法应当放在projectService中，
查询项目所有的评论应当放在commentService中

1. 添加、更新、删除操作，若有关联表（多对多关系），则将方法放在关联pojoService中，若没有则将方法放在被动关系pojoService中，当添加更新操作需要联动其他表时，应当在service层完成联动，联动必须是调用其他service方法， __不得__ 去调用mapper并自写方法，不下放至mapper层。
 >例如：用户向文章添加评论时，应当将方法放在commentService中，并在方法内完成项目评论给数+1操作（调用项目service自增方法，若无此方法，则去该service创建, __不能__ 去调用项目mapper中的自增方法）。
 
1. 添加、更新操作，若需要对传入数据进行默认封装，应当在本层进行


