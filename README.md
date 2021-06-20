LogClient
	With start, end and poll.
	Poll event will remove the queue.
	When end happens then only print, otherwise just hold
	
	Step1:
		Process Model with process id, start time and end time
		
			Key: id, start final? 
				they are not going to change after process is contructed
			Key: Constructor only takes Id and start(for test)
				end is calculated hence not taken as input.
				
	Step2: 
		LoggerImplementation implements Interface LogClient
		
	
	Step3:
		For List of Process you can use
			1. LinkList: List is Not good idea 
					CRUD
						. Good(C)Start you can add to link list quickly
						. Bad(U) But when you have to end, that will have to travel liner on list.(Not Good for Updated)
				Create List and initiate a blank ArrayList
		
			Setup start process to add process to our list
			End Process will face issue to get the process to end. As you will have to loop to find.
			
			So you need map
			
			2. Use Map with processid as key and Process as value.
				
				
			
			

				