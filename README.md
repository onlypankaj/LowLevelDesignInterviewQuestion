LogClient
	With start, end and poll.
	Poll event will remove the queue.
	When end happens then only print, otherwise just hold

################### STAGE1 BASIC ##########################################
	
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
					End will be slow.
					
					CRUD
						. Good(C)Start you can add to link list quickly
						. Bad(U) But when you have to end, that will have to travel liner on list.(Not Good for Updated)
				Create List and initiate a blank ArrayList
		
			Setup start process to add process to our list
			End Process will face issue to get the process to end. As you will have to loop to find.
			
			So you need map
			
			2. Use Map with processid as key and Process as value.
					initialize as HashMap
					instead of add use put
					
					Poll will be slow.
					
					CRUD
						. Good(R, where clause) Can find easily the processid with key
						. Bad(R, order by) Find smallest start time, bcz Map has no ordering.
						. Bad(R, All) is slow.
						
						Option TreeMap
						
			3. Use TreeMap
					It has order and can use firstEntry method directly
					Poll is slow.
					
					CRUD
						. Good(R, order by) as TreeMap is already ordered.
						. Bad(R, where clause) poll will become slow.
						. Bad(R, order by starttime) TreeMap will order by Key which is processid, whereas we want order by Starttime
						
					Order by Processid is not required, order by starttime is required.
					
			4. Use Heap
					Start will be fast becuase Heap hepifince itself if required.
					Poll will be fast, as select all
					but end will be slow, becuase it will have to search Process.
					
					CRUD
						.Bad(R, where clause)You cant do search operation effecitantly
						.Good(R, All) You can pull out maximum, which means your poll will be fast.
						
			5. Use Complicated data structure: No data Structure can perform all start, end and poll fast
					Heap+Map --> Will also work, pripority queue in java is implemented with Heap
					TreeMap + Map --> Will also work, which we will see
					
					a. TreeMap, will act as queue, you can add and poll from this queue
						CRUD
						.Good(R, order by) poll firstEntry
						
					b. HashMap, will act like index, so where clause search will be fast, end will be fast. With Processid as input.
						CRUD
						.Good(R, where clause) can go directly to value
						
################### STAGE1 COMPLETED ##########################################


################### STAGE2 CONCURRENCY ##########################################

						
						
				
			
			

				