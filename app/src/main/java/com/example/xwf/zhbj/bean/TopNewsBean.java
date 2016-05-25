package com.example.xwf.zhbj.bean;

import java.util.List;

/**
 * Created by Hsia on 16/5/25.
 * E-mail: xiaweifeng@live.cn
 * //TODO:新闻中心的顶部新闻的Bean对象
 */
public class TopNewsBean {

    /**
     * retcode : 200
     * data : {"title":"财经","topnews":[{"id":35301,"title":"财经1","topimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","pubdate":"2014-04-08 14:24","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35301","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35125,"title":"财经2","topimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU92.jpg","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","pubdate":"2014-04-08 09:09","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"}],"topic":[{"title":"财经","id":10101,"url":"http://10.0.2.2:8080/zhbj/10011/list_1.json","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","description":"11111111","sort":1},{"title":"222222222222","id":10100,"url":"http://10.0.2.2:8080/zhbj/10011/list_1.json","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","description":"22222222","sort":2}],"news":[{"id":35311,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35312,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35313,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35314,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35315,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35316,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35310,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35318,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35314,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:54","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35314","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35117,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 08:45","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35117","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"}],"countcommenturl":"http://zhbj.qianlong.com/client/content/countComment/","more":"/10011/list_2.json"}
     */

    private int retcode;
    /**
     * title : 财经
     * topnews : [{"id":35301,"title":"财经1","topimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","pubdate":"2014-04-08 14:24","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35301","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35125,"title":"财经2","topimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU92.jpg","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","pubdate":"2014-04-08 09:09","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35125","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"}]
     * topic : [{"title":"财经","id":10101,"url":"http://10.0.2.2:8080/zhbj/10011/list_1.json","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","description":"11111111","sort":1},{"title":"222222222222","id":10100,"url":"http://10.0.2.2:8080/zhbj/10011/list_1.json","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","description":"22222222","sort":2}]
     * news : [{"id":35311,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35312,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35313,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35314,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35315,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35316,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35310,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35318,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:58","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35319","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35314,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 14:54","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35314","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"},{"id":35117,"title":"财经","url":"http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html","listimage":"http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg","pubdate":"2014-04-08 08:45","comment":true,"commenturl":"http://zhbj.qianlong.com/client/user/newComment/35117","type":"news","commentlist":"http://10.0.2.2:8080/zhbj/10011/comment_1.json"}]
     * countcommenturl : http://zhbj.qianlong.com/client/content/countComment/
     * more : /10011/list_2.json
     */

    private DataBean data;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String title;
        private String countcommenturl;
        private String more;
        /**
         * id : 35301
         * title : 财经1
         * topimage : http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg
         * url : http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html
         * pubdate : 2014-04-08 14:24
         * comment : true
         * commenturl : http://zhbj.qianlong.com/client/user/newComment/35301
         * type : news
         * commentlist : http://10.0.2.2:8080/zhbj/10011/comment_1.json
         */

        private List<TopnewsBean> topnews;
        /**
         * title : 财经
         * id : 10101
         * url : http://10.0.2.2:8080/zhbj/10011/list_1.json
         * listimage : http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg
         * description : 11111111
         * sort : 1
         */

        private List<TopicBean> topic;
        /**
         * id : 35311
         * title : 财经
         * url : http://10.0.2.2:8080/zhbj/10011/724D6A55496A11726628.html
         * listimage : http://10.0.2.2:8080/zhbj/10011/1452327318UU91.jpg
         * pubdate : 2014-04-08 14:58
         * comment : true
         * commenturl : http://zhbj.qianlong.com/client/user/newComment/35319
         * type : news
         * commentlist : http://10.0.2.2:8080/zhbj/10011/comment_1.json
         */

        private List<NewsBean> news;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCountcommenturl() {
            return countcommenturl;
        }

        public void setCountcommenturl(String countcommenturl) {
            this.countcommenturl = countcommenturl;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public List<TopnewsBean> getTopnews() {
            return topnews;
        }

        public void setTopnews(List<TopnewsBean> topnews) {
            this.topnews = topnews;
        }

        public List<TopicBean> getTopic() {
            return topic;
        }

        public void setTopic(List<TopicBean> topic) {
            this.topic = topic;
        }

        public List<NewsBean> getNews() {
            return news;
        }

        public void setNews(List<NewsBean> news) {
            this.news = news;
        }

        public static class TopnewsBean {
            private int id;
            private String title;
            private String topimage;
            private String url;
            private String pubdate;
            private boolean comment;
            private String commenturl;
            private String type;
            private String commentlist;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTopimage() {
                return topimage;
            }

            public void setTopimage(String topimage) {
                this.topimage = topimage;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }
        }

        public static class TopicBean {
            private String title;
            private int id;
            private String url;
            private String listimage;
            private String description;
            private int sort;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getListimage() {
                return listimage;
            }

            public void setListimage(String listimage) {
                this.listimage = listimage;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }

        public static class NewsBean {
            private int id;
            private String title;
            private String url;
            private String listimage;
            private String pubdate;
            private boolean comment;
            private String commenturl;
            private String type;
            private String commentlist;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getListimage() {
                return listimage;
            }

            public void setListimage(String listimage) {
                this.listimage = listimage;
            }

            public String getPubdate() {
                return pubdate;
            }

            public void setPubdate(String pubdate) {
                this.pubdate = pubdate;
            }

            public boolean isComment() {
                return comment;
            }

            public void setComment(boolean comment) {
                this.comment = comment;
            }

            public String getCommenturl() {
                return commenturl;
            }

            public void setCommenturl(String commenturl) {
                this.commenturl = commenturl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCommentlist() {
                return commentlist;
            }

            public void setCommentlist(String commentlist) {
                this.commentlist = commentlist;
            }
        }
    }
}
