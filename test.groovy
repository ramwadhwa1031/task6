job('GitHub-Code') {
	triggers {
        upstream('test', 'SUCCESS')
    }
    scm {
        github('ramwadhwa1031/task6', 'master')
    }
    steps {
       shell(''' sudo cp * -v /task6'
       sudo docker build -t d1031/web-server:v1
       sudo docker push d1031/web-server:v1
       ''')
    }
}
job('Deployment') {
	triggers {
        upstream('GitHub-Code', 'SUCCESS')
    }
    
    steps {
       shell(''' fullfilename="/task6/*.html"
			filename=$(basename "$fullfilename")
			ext="${filename##*.}"
			echo $ext
           
			if [ $ext == html ];
			then
			   if   kubectl get deployment | grep webserver 
			   then
			      echo "Already Running"
				else
				  kubectl create -k /task6/
				 fi
				 
			elif [ $ext == php ];
			then
			   if   kubectl get deployment | grep webserver-php 
			   then
				  echo "Already Running"
			      
				else
			  fi	 
			else
			   echo "everything is working"
			fi	 
				 
			''')
    }
}

job('Testing') {
	triggers {
        upstream('Deployment', 'SUCCESS')
    }
    
    steps {
       shell('''
	    status=$(curl -o /dev/null -s "%{http_code}" http://192.168.99.100:32750)
			if [[$status == 200 ]]
			then
			echo "running"
			sudo python3 /task6/successmail.py
			else
			echo "failure"
			sudo python3 /task6/failuremail.py
			fi 
          ''')
    }
}
