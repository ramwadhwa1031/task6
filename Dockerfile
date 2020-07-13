FROM centos
RUN yum install httpd -y
COPY index.html about.html /var/www/html/
EXPOSE 80
CMD /usr/sbin/httpd -DFOREGROUND
