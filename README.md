
# 接口文档
## 目录

[TOC]

版本修订信息
|版本号|说明|修订人|
|-------|--------|
|1.0|初始版本，修订比较草率|omsfuk、Eric|
|2.0|完全重写|omsfuk|

## 格式说明
```
/api/{版本号}/xxxxxxx
```



## 用户身份验证

#### 登录
`POST /api/v2/user/signIn`

|参数|必选|类型|说明|
|-----|-----|
|username|No|String|用户名|
|phone|No|String|手机号|
|email|No|String|邮箱|
|password|Yes|String|密码|
备注：username、phone、email至少有一个不为空。

登录成功
```
{
	status: 200,
	message: "ok",
	data: {
		id: 100,
		username: "omsfuk",
		phone:"11100011100",
		email:"123@123.com",
		signature:"66666",
		birthday:"1999-01-01",
		mark:1000,
		gender:"男",
		fans: 10,
		type: 1
	}
}
```
登录失败
```
{
	status: 3xx,
	message: "failure"
}
```

错误码
```
302 no such user
303 wrong password
```


#### 注册
`POST /api/v2/user/signUp`

|参数|必选|类型|说明|
|-----|-----|-----|-----|
|username|Yes|String|用户名。只能以数字、字母作为用户名。首字符不能为数字|
|phone|No|String|手机号|
|email|No|String|邮箱|
|password|No|String|密码|
|code|No|String|验证码。当此项非空时，其他参数可忽略|
备注：phone、email至少有一个不为空。会发送验证短信（邮件）。可通过此方法验证用户是否注册，此时不需要填写密码。

验证成功
```
{
	status: 200,
	message: "ok",
	data: {
		id: 100,
		username: "omsfuk",
		phone:"11100011100",
		email:"123@123.com",
		signature:"66666",
		birthday:"1999-01-01",
		mark:1000,
		gender:"男",
		fans: 10,
		type: 1
	}
}
```
验证失败
```
{
	status: 3xx,
	message: "failure",
	data: {}
}
```

错误码
```
304 user already exists
```

#### 修改用户信息
`POST /api/v2/user/update`

|参数|必选|类型|说明|
|-----|-----|-----|-----|
|id|Yes|int|用户id|
|type|Yes|int|用户类型|
|gender|Yes|string|性别|
|username|Yes|string|用户名|
|password|Yes|string|密码|
|phone|Yes|string|手机号。改动会引起验证流程|
|email|Yes|string|邮箱。改动会引起验证流程|
|realName|Yes|string|真实姓名。改动会引起验证流程|
|realId|Yes|string|身份证号码。改动会引起验证流程|
|signature|Yes|string|签名|
|birthday|Yes|string|生日|
备注：验证流程有待商榷。

响应
```
{
	status: 200,
	message: "ok",
	data: {
		id: 100,
		username: "omsfuk",
		phone:"11100011100",
		email:"123@123.com",
		signature:"66666",
		birthday:"1999-01-01",
		mark:1000,
		gender:"男",
		fans: 10,
		type: 1
	}
}
```

#### 修改用户头像
`POST /api/v2/user/portrait/update`

|参数|必选|类型|说明|
|-----|-----|-----|-----|
|img|Yes|File|图片文件。支持png，jpg，自动后台生成小图片。尾缀加sm获得小图片|

响应
```
response:
{
	status: 200,
	message: "ok",
	data: {}
}
```



#### 获取用户信息
`POST /api/v2/user/find`

|参数|必选|类型|说明|
|-----|-----|-----|-----|
|id|No|int|用户id|
|username|No|string|用户名|
备注：id和username任选其一。

响应
```
{
	status:200
	message:"OK"
	data:{
		id: 100,
		username: "omsfuk",
		phone:"11100011100",
		email:"123@123.com",
		signature:"66666",
		birthday:"1999-01-01",
		mark:1000,
		gender:"男",
		fans: 10,
		type: 1
	}
}
```
错误码
```
302 no such user
```

## 商品
#### 上传商品
`POST /api/v2/goods/add`

|属性名|必选|类型|说明|
|-|-|
|type|No|int|商品种类|
|title|Yes|string|标题|
|desc|No|string|描述|
|l1|Yes|string|一级地址。如济南市|
|l2|Yes|string|二级地址。如历下区|
|l3|Yes|string|三级地址。如xxx|
|longitude|Yes|double|经度。153.12|
|latitude|Yes|double|纬度。75.12|
|deadline|Yes|string|截止时间。2017-10-11|
|pic|No|File[]|展示图片|

上传成功
```
{
	status: 200,
	message: "ok",
	data: {
		id: 12,
		type: 1,
		title: "牙签",
		desc: "lalalalala",
		l1: "济南市"，
		l2: "历下区"，
		l3: "xxx",
		latitude: "123.23",
		longitude: "13.1",
		date: "2017-9-10",
		deadline: "2017-12-01",
		username: "omsfuk",
		pic: ["/img/234abe234bac7621.png", "/img/234abe234bac7621.png"]
	}
}
```

#### 删除商品
`POST /api/v2/goods/delete`

|属性名|必选|类型|说明|
|-|-|-|
|goodId|Yes|int|商品id|

响应
```
{
	status: 200,
	message: "ok",
	data: {}
}
```


#### 获取商品
`POST /api/v2/goods/find`

|属性名|必选|类型|说明|
|-|-|-|
|id|No|int|物品id|
|type|No|int|类型|物品类型|
|username|No|string|类型|
|keyword|No|string|关键词。会搜索title和desc|
|l1|No|string|一级地址|
|l2|No|string|二级地址|
|l3|No|string|三级地址|
|page|No|int|页数，从0开始|
|rows|No|int|每页行数|

响应
```
{
	status: 200,
	message: ok,
	data: [
		{
			id: 12,
			type: 1,
			title: "牙签",
			desc: "lalalalala",
			l1: "济南市"，
			l2: "历下区"，
			l3: "xxx",
			latitude: "123.23",
			longitude: "13.1",
			date: "2017-9-10",
			deadline: "2017-12-01",
			userId: 1,
			username: "omsfuk",
			pic: ["/img/234abe234bac7621.png", "/img/234abe234bac7621.png"]
		},
		{
			id: 11,
			type: 1,
			title: "牙膏",
			desc: "lalalalala",
			l1: "济南市"，
			l2: "历下区"，
			l3: "xxx",
			latitude: "123.23",
			longitude: "13.1",
			date: "2017-9-10",
			deadline: "2017-12-01",
			userId: 1,
			username: "omsfuk",
			pic: ["/img/234abe234bac7621.png", "/img/234abe234bac7621.png"]
		}
	]
}
```

## 收藏夹

#### 获得收藏
`POST /api/v2/collection/find`

|参数|必选|类型|说明|
|-|-|-|-|
|page|No|int||
|rows|No|int||

响应
```
{
	status: 200,
	message: "ok",
	data: [
		{
			id: 12,
			type: 1,
			title: "牙签",
			desc: "lalalalala",
			l1: "济南市"，
			l2: "历下区"，
			l3: "xxx",
			latitude: "123.23",
			longitude: "13.1",
			date: "2017-9-10",
			deadline: "2017-12-01",
			userId: 1,
			username: "omsfuk",
			pic: ["/img/234abe234bac7621.png", "/img/234abe234bac7621.png"]
		},
		{
			id: 11,
			type: 1,
			title: "牙膏",
			desc: "lalalalala",
			l1: "济南市"，
			l2: "历下区"，
			l3: "xxx",
			latitude: "123.23",
			longitude: "13.1",
			date: "2017-9-10",
			deadline: "2017-12-01",
			userId: 1,
			username: "omsfuk",
			pic: ["/img/234abe234bac7621.png", "/img/234abe234bac7621.png"]
		}
	]
}
```

#### 添加收藏
`POST /api/v2/collection/add`

|属性名|必选|类型|说明|
|-|-|-|-|
|goodsId|Yes|int|商品id|

响应
```
{
	status: 200,
	message: "ok",
	data: {
		{	
			id: 11,
			type: 1,
			title: "牙膏",
			desc: "lalalalala",
			l1: "济南市"，
			l2: "历下区"，
			l3: "xxx",
			latitude: "123.23",
			longitude: "13.1",
			date: "2017-9-10",
			deadline: "2017-12-01",
			userId: 1,
			username: "omsfuk",
			pic: ["/img/234abe234bac7621.png", "/img/234abe234bac7621.png"]
		}	
	}
}
```

#### 删除收藏
`POST /api/v2/collection/delete`


|属性名|必选|类型|说明|
|-|-|-|-|
|goodsId|Yes|int|商品id|

响应
```
{
	status: 200,
	message: "ok",
	data: {}
}
```

## 历史记录
#### 获得历史记录
`POST /api/v2/history/find`

|属性名|必选|类型|说明|
|-|-|-|-|
|page|Yes|int||
|rows|Yes|int||

响应
```
{
	status: 200,
	message: "ok",
	data: [
		{
			id: 12,
			type: 1,
			title: "牙签",
			desc: "lalalalala",
			l1: "济南市"，
			l2: "历下区"，
			l3: "xxx",
			latitude: "123.23",
			longitude: "13.1",
			date: "2017-9-10",
			deadline: "2017-12-01",
			userId: 1,
			username: "omsfuk",
			pic: ["/img/234abe234bac7621.png", "/img/234abe234bac7621.png"]
		},
		{
			id: 11,
			type: 1,
			title: "牙膏",
			desc: "lalalalala",
			l1: "济南市"，
			l2: "历下区"，
			l3: "xxx",
			latitude: "123.23",
			longitude: "13.1",
			date: "2017-9-10",
			deadline: "2017-12-01",
			userId: 1,
			username: "omsfuk",
			pic: ["/img/234abe234bac7621.png", "/img/234abe234bac7621.png"]
		}
	]
}
```


## 关注

#### 获得我关注的
`POST /api/vi/followed/find`

|属性名|必选|类型|说明|
|-|-|-|-|
|page|No|int||
|rows|No|int||

响应
```
{
	status: 200,
	message: "ok",
	data: [
		{
			id: 100,
			username: "omsfuk",
			phone:"11100011100",
			email:"123@123.com",
			signature:"66666",
			birthday:"1999-01-01",
			mark:1000,
			gender:"男",
			fans: 10,
			type: 1
		},
		{
			id: 100,
			username: "omsfuk",
			phone:"11100011100",
			email:"123@123.com",
			signature:"66666",
			birthday:"1999-01-01",
			mark:1000,
			gender:"男",
			fans: 10,
			type: 1
		}
	]
}
```

#### 删除关注
`POST /api/vi/followed/delete`

|属性名|必选|类型|说明|
|-|-|-|-|
|userId|Yes|int|用户id|

响应
```
{
	status: 200,
	message: "ok",
	data: {}
}
```

#### 添加关注
`POST /api/vi/followed/add`

|属性名|必选|类型|说明|
|-|-|-|-|
|userId|Yes|int|用户id|

响应
```
{
	status: 200,
	message: "ok",
	data: {}
}
```

## 杂项
#### 关键词提示
```
POST /api/v2/keywords
```
|属性名|必选|类型|说明|
|-|-|-|-|
|keyword|Yes|string|部分关键词|

响应
```
{
	status: 200,
	message: "ok",
	data: [
		"title1",
		"title2",
		"title3"
	]
}
```



## 评论

#### 添加评论
```
POST /api/v2/comment/add
```
|属性名|必选|类型|说明|
|-|-|-|-|
|goodId|Yes|int|商品id|
|cont|Yes|string|评论内容|

响应
```
{
	status: 200
	message: “ok”,
	data: {
	}
}
```

#### 删除评论
`POST /api/v2/comment/delete`

|属性名|必选|类型|说明|
|-|-|-|-|
|commentId|Yes|int|评论id|
		
#### 获取评论
`POST /api/v2/comment/find`

|属性名|必选|类型|说明|
|-|-|-|-|
|id|No|int|评论id|
|goodsId|No|int|商品id|
|userId|No|int|评论者id|
|page|No|int||
|rows|No|int||
备注：复合查询。id、goodsId、userId可以构成复合查询。

响应
```
{
	status: 200,
	message: "ok",
	data: [
		{
		    id: 1, 
		    goodId: 2,
		    userId: 1,
			username: "omsfuk",
		    content: "abc",
   			date: "2016-01-02 12:40:00"
		},
		{
		    id: 1, 
		    goodId: 2,
		    userId: 1,
			username: "omsfuk",
		    content: "abc",
   			date: "2016-01-02 12:40:00"
		}
	]
}
```








## 全局错误码
```
301 Unauthorized
```


## 附录
#### 用户属性

|属性名称|类型|说明|
|-|-|-|
|id|int|用户唯一标识|
|username|string|用户名，唯一标志|
|phone|string|手机号码，唯一标识，可为空|
|email|string|邮箱，唯一标志，可为空|
|signature|string|签名|
|mark|int|积分|
|type|int|用户类型，普通用户或者商家|
|password|string|密码|
|gender|string|性别|
|birthday|string|生日，格式1901-01-01|
|fans|int|粉丝数量|


#### 商品属性
|属性名|类型|说明|
|-|-|
|id|int|唯一标识|
|type|int|商品种类。此属性有待商榷。后期可采用标签的形式，给物品分类|
|title|string|标题|
|desc|string|描述|
|l1|string|一级地址|
|l2|string|二级地址|
|l3|string|三级地址|
|longitude|double|经度|
|latitude|double|纬度|
|date|string|上传时间|
|deadline|string|截止时间|
|username|string|上传者用户名|
|pic|string|展示图片|


## 备注
自动添加历史记录。

