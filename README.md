LogClient
	With start, end and poll.
	Poll event will remove the queue.
	
	Step1:
		Process Model with process id, start time and end time
		
			Key: id, start final? 
				they are not going to change after process is contructed
			Key: Constructor only takes Id and start(for test)
				end is calculated hence not taken as input.
				
				