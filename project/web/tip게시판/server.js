var http = require("http");
var express = require("express");
var app = express();
app.use(express.static("public"))
app.use(express.bodyParser())
app.use(app.router)

const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');
const url = 'mongodb://localhost:27017';
const dbName = 'camp';
var ObjectID = require('mongodb').ObjectID

const N = 10;
var totalPage = 0;
var pageNum = 1;


// app.all("/updateRC", function(req,res){
app.all("/updateCMD", function(req,res){
    // console.log("CMD 업데이트로 오나?");
    var type = req.param("type")
    console.log(type);
    var id = new ObjectID(req.param("tip_id"));
    var doc;
    switch(type){
        case "rc":
            var rc = parseInt(req.param("rc"));
            doc = {"rc":rc};
            break;
        case "dc":
            var dc = parseInt(req.param("dc"));
            doc = {"dc":dc};    
            break;
        case "rp":
            var rp = parseInt(req.param("rp"));
            console.log("서버의 rp값:"+rp);
            doc = {"report":rp}; 
            break;
    }    
    // console.log("** ID값 확인 : "+id);
    // console.log("** RC값 확인 : "+rc);
    // console.log("** DC값 확인 : "+dc);
    var q = {_id:id};
    const client = new MongoClient(url);
    client.connect(function(err, client) {
        assert.equal(null, err);
        console.log("Connected correctly to server");
        const db = client.db(dbName);
        const col = db.collection('tip');
        col.updateOne(q, {$set:doc}, {returnOriginal: false, upsert: true}, 
            function(err, r) {
                res.send("1");
            });
        client.close();
    });
});


app.post("/comments", function(req, res){
    console.log("comment 오나?");
    var id = new ObjectID(req.param("_id"));
    var cm_writer = req.param("cm_writer");
    var cm_content = req.param("cm_content");
    var cm_date = new Date();
    console.log("** ID값 확인 : "+id);
    console.log("** writer 확인 : "+cm_writer);
    console.log("** content 확인 : "+cm_content);
    console.log(cm_date);

    var q = {_id:id};
    var doc = {comment:{"cm_writer":cm_writer, "cm_content":cm_content, "cm_date":cm_date}};
    var doc2 = {"$push":doc};

    const client = new MongoClient(url);
    client.connect(function(err, client) {
        assert.equal(null, err);
        console.log("Connected correctly to server");
        const db = client.db(dbName);
        const col = db.collection('tip');
        col.findOneAndUpdate(q, doc2, {returnOriginal: false, upsert: true}, 
            function(err, r) {
                console.log("** 여기까진 오는겨?");
                res.send(doc.comment);
            });
        client.close();
    });

    // var commenter_name = req.param("commenter_name");
    // var commenter_email = req.param("commenter_email");
    // var comment = req.param("comment");
    // var id = req.param("articles_id");
    // var posted_at = new Date();

    // var doc = {comment:{commenter_name:commenter_name, commenter_email:commenter_email, comment:comment, posted_at:posted_at}};
    // var doc2 = {"$push":doc};
    // var q = {_id: new ObjectID(id)};
    // console.log("server1024의 q:"+q);

    // const client = new MongoClient(url);
    // client.connect(function(err, client) {
    // assert.equal(null, err);
    // console.log("Connected correctly to server");
    // const db = client.db(dbName);
    // const col = db.collection('articles');

    // col.findOneAndUpdate(q, doc2, {returnOriginal: false, upsert: true}, 
    //     function(err, r) {
    //         res.send(doc.comment);
    //     });
    // client.close();
    // });

});

app.post("/update", function(req, res){
    console.log("업데이트로 오나?");
    var id = new ObjectID(req.param("_id"));
    // console.log("** ID값 확인 : "+id);
    var title = req.param("title");
    var writer = req.param("writer");
    var content = req.param("content");
    var imgName = "";
    var doc = {"title":title, "writer":writer, "content":content, "img":imgName};
    var q = {_id:id};
    const client = new MongoClient(url);
    client.connect(function(err, client) {
        assert.equal(null, err);
        console.log("Connected correctly to server");
        const db = client.db(dbName);
        const col = db.collection('tip');
        col.findOneAndUpdate(q, {$set:doc}, {returnOriginal: false, upsert: true}, 
            function(err, r) {
                res.send("1");
            });
        client.close();
    });
});

app.all("/delete", function(req,res){
    var id = new ObjectID(req.param("tip_id"));
    // console.log("** server의 detail : "+id);
    var q = {_id:id};
    const client = new MongoClient(url);
    client.connect(function(err, client) {
        assert.equal(null, err);
        console.log("Connected correctly to server");
        const db = client.db(dbName);
        const col = db.collection('tip');
        col.deleteOne(q, function(err, r) {
            res.send(r);
            client.close();
        });
    });

});


app.all("/tipDetail", function(req, res){
    var id = new ObjectID(req.param("tip_id"));
    // console.log("** server의 detail : "+id);
    var q = {_id:id};
    const client = new MongoClient(url);
    client.connect(function(err, client) {
        assert.equal(null, err);
        console.log("Connected correctly to server");
        const db = client.db(dbName);
        const col = db.collection('tip');
        col.find(q).toArray(function(err, docs) {
        // col.find({"_id":ObjectId(id)}).toArray(function(err, docs) {
            res.send(docs[0]);
            client.close();
        });
    });
})

app.post("/insert", function(req, res){
    var title = req.param("title");
    var writer = req.param("writer");
    var content = req.param("content");
    var imgName = "";
    var date = new Date();
    var doc = {"title":title, "writer":writer, "content":content, "img":imgName, "date":date, "rc":0, "dc":0, "report":0};

    const client = new MongoClient(url);
    client.connect(function(err, client) {
        assert.equal(null, err);
        console.log("Connected correctly to server");
        const db = client.db(dbName);
        db.collection('tip').insertOne(doc, function(err, r) {
            // assert.equal(null, err);
            // assert.equal(1, r.insertedCount);
            res.send("1");
            client.close();
        });
    });
});

app.all("/tipCount", function(req, res){
    pageNum = req.param("pageNum");
    searchType = req.param("searchType");
    searchKeyword = req.param("searchKeyword");
    var q = {};
    switch (searchType){
        case "title":
            var q = { "title" : {$regex : new RegExp(searchKeyword)} };
            break;
        case "writer":
            var q = { "writer" : {$regex : new RegExp(searchKeyword)} };            
            break;
        case "content":
            var q = { "content" : {$regex : new RegExp(searchKeyword)} };
            break;
    }

    const client = new MongoClient(url);
    client.connect(function(err, client) {
        assert.equal(null, err);
        console.log("Connected correctly to server");
        const db = client.db(dbName);
        const col = db.collection('tip');
        col.count(q, function(e, count) {
            if(count%N == 0){
                totalPage = count / N;
            } else {
                totalPage = count / N + 1;
            }
            // console.log("totalPage : "+totalPage);
            res.send(totalPage+"");
            client.close();
        });
    });
});

app.all("/tipList", function(req, res){
    // pageNum = req.param("pageNum");
    searchType = req.param("searchType");
    searchKeyword = req.param("searchKeyword");

    console.log("** tipList : "+pageNum +" / "+searchType+" / "+searchKeyword);
    
    var q = {};
    switch (searchType){
        case "title":
            var q = { "title" : {$regex : new RegExp(searchKeyword)} };
            break;
        case "writer":
            var q = { "writer" : {$regex : new RegExp(searchKeyword)} };            
            break;
        case "content":
            var q = { "content" : {$regex : new RegExp(searchKeyword)} };
            break;
    }

    const client = new MongoClient(url);
    client.connect(function(err, client) {
        pageNum = req.param("pageNum");
        assert.equal(null, err);
        console.log("Connected correctly to server");
        const db = client.db(dbName);
        const col = db.collection('tip');

        console.log("** tipList의 client 메소드 내부 : "+pageNum +" / "+searchType+" / "+searchKeyword);

        col.find(q).sort({"date":-1}).limit(N).skip((pageNum-1)*N).toArray(function(err, docs) {
            res.send(docs);
            console.log("docs.length : "+docs.length);
            client.close();
        });
    });
});

http.createServer(app).listen(52273, function(){
    console.log("** Server Start! **");
});