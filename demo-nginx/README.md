# 诉求

系统重构，流量切换；

举个例子：

原来使用的a站点，现在我重新实现了，叫做b站点；

用户点击a站点的入口，跳转到b站点。

貌似只需要一个 rewrite 配置;



# reWrite模块检查

>nginx version: nginx/1.22.0
built by gcc 11.2.1 20220219 (Alpine 11.2.1_git20220219)
built with OpenSSL 1.1.1o  3 May 2022
TLS SNI support enabled
configure arguments: --prefix=/etc/nginx --sbin-path=/usr/sbin/nginx --modules-path=/usr/lib/nginx/modules --conf-path=/etc/nginx/nginx.conf --error-log-path=/var/log/nginx/error.log --http-log-path=/var/log/nginx/access.log --pid-path=/var/run/nginx.pid --lock-path=/var
/run/nginx.lock --http-client-body-temp-path=/var/cache/nginx/client_temp --http-proxy-temp-path=/var/cache/nginx/proxy_temp --http-fastcgi-temp-path=/var/cache/nginx/fastcgi_temp --http-uwsgi-temp-path=/var/cache/nginx/uwsgi_temp --http-scgi-temp-path=/var/cache/nginx/s
cgi_temp --with-perl_modules_path=/usr/lib/perl5/vendor_perl --user=nginx --group=nginx --with-compat --with-file-aio --with-threads --with-http_addition_module --with-http_auth_request_module --with-http_dav_module --with-http_flv_module --with-http_gunzip_module --with
-http_gzip_static_module --with-http_mp4_module --with-http_random_index_module --with-http_realip_module --with-http_secure_link_module --with-http_slice_module --with-http_ssl_module --with-http_stub_status_module --with-http_sub_module --with-http_v2_module --with-mai
l --with-mail_ssl_module --with-stream --with-stream_realip_module --with-stream_ssl_module --with-stream_ssl_preread_module --with-cc-opt='-Os -fomit-frame-pointer -g' --with-ld-opt=-Wl,--as-needed,-O1,--sort-common



# 实验

```shell
 docker run --name nginx2 -p 8000:80 -d nginx:mainline-perl
```

进入里面去增加配置，进行实验。




# 参考资料

[nginx的rewrite使用举例](https://blog.csdn.net/tojinzi/article/details/119837993)
[nginx的rewrite配置比较全的举例](https://blog.csdn.net/weixin_50344814/article/details/110459900)
[nginx官方rewrite介绍](https://nginx.org/en/docs/http/converting_rewrite_rules.html)