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
       shell('fullfilename="/task6/index.html"
			filename=$(basename "$fullfilename")
			ext="${filename##*.}"
			echo $ext')
    }

}

