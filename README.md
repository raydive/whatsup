# Abc Metric #

## Build & Run ##

```sh
$ heroku login
$ heroku create
$ git push heroku master
```

https://polar-cove-36737.herokuapp.com/abc に対してPostでjsonを投げるとAbc metricを計算しresponseでjsonを返します。
今のところTypetalkでのbot運用を想定したものとなっています。

jsonの例

```json
{
  "topic": {
    "id": 208,
    "name": "IT Peeps",
    "isDirectMessage": false,
    "lastPostedAt": "2014-12-10T09:00:29Z",
    "createdAt": "2014-06-10T02:32:29Z",
    "updatedAt": "2014-06-10T02:32:29Z"
  },
  "post": {
    "id": 333,
    "topicId": 208,
    "replyTo": null,
    "message": "@abc_test+ word = 1\ntest = 2 + word\nif word == 1\n          unless test != 2\n            word += test\n          end\n        end\n        print(test)",
    "account": {
      "id": 100,
      "name": "foo",
      "fullName": "foo",
      "suggestion": "fooooooooo",
      "imageUrl": "https://typetalk.in/accounts/100/profile_image.png?t=1403577149000",
      "isBot": false,
      "createdAt": "2014-06-24T02:32:29Z",
      "updatedAt": "2014-06-24T02:32:29Z"
    },
    "mention": null,
    "attachments": [],
    "likes": [],
    "talks": [],
    "links": [],
    "createdAt": "2014-12-10T09:00:29Z",
    "updatedAt": "2014-12-10T09:00:29Z"
  },
  "mentions": [],
  "exceedsAttachmentLimit": false,
  "directMessage": null
}
```