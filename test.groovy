job('GitHub-Code') {
	triggers {
        upstream('test', 'SUCCESS')
    }
    scm {
        github('ramwadhwa1031/DevopsHw', 'master')
    }
    steps {
       shell('sudo cp * -v /task6')
    }

}
job('Deployment') {
	triggers {
        upstream('GitHub-Code', 'SUCCESS')
    }
    
    steps {
       shell('''fullfilename="/task6/*.html"
			filename=$(basename "$fullfilename")
			ext="${filename##*.}"
			echo $ext
			if [ $ext == html]
			then
			   if sudo kubectl get deployment | grep webserver
			   then
			      echo "it is already running"
				else
				  echo "creating"
				 fi
			else
			   echo "everything is wokring"
			fi	 
			''')
    }

}

