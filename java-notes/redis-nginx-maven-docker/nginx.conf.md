
	server {
		listen			80;
		server_name 	aaa;            //名字随意
		root        	/nginx/www;
		index			index.php index.html index.html;
		charset			utf-8;
		location / {
			proxy_pass http://vstest;   //对应下面的upstream 后面的名字
			add_header Access-Control-Allow-Origin *;
		}
		location /favicon.ico {
            root html;
        }
	}


	upstream vstest {
		server nginx.tomcat.com:8080 weight=1;
		server nginx.tomcat.com:8082 weight=1;
	}
	
### 	log_format

	$remote_addr             客户端地址                                    211.28.65.253
    $remote_user             客户端用户名称                                --
    $time_local              访问时间和时区                                18/Jul/2012:17:00:01 +0800
    $request                 请求的URI和HTTP协议                           "GET /article-10000.html HTTP/1.1"
    $http_host               请求地址，即浏览器中你输入的地址（IP或域名）     www.wang.com 192.168.100.100
    $status                  HTTP请求状态                                  200
    $upstream_status         upstream状态                                  200
    $body_bytes_sent         发送给客户端文件内容大小                        1547
    $http_referer            url跳转来源                                   https://www.baidu.com/
    $http_user_agent         用户终端浏览器等信息                           "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; SV1; GTB7.0; .NET4.0C;
    $ssl_protocol            SSL协议版本                                   TLSv1
    $ssl_cipher              交换数据中的算法                               RC4-SHA
    $upstream_addr           后台upstream的地址，即真正提供服务的主机地址     10.10.10.100:80
    $request_time            整个请求的总时间                               0.205
    $upstream_response_time  请求过程中，upstream响应时间                    0.002
    
    log_format  main  '$time_local | From $remote_addr $http_host | $request | $status | size $body_bytes_sent | '
                      '$http_referer | supplier IP $upstream_addr | $upstream_response_time | $request_time';

    access_log  logs/access.log main;