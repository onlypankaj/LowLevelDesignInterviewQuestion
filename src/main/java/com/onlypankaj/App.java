package com.onlypankaj;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )    {

        final LogClient logger = new LoggerImplementation();

        //output
        //1
        //3
        //2

        logger.start("1");
        logger.poll();                      // No Completed Task
        logger.start("3");
        logger.poll();                      // No Completed Task
        logger.end("1");
        logger.poll();                      // 1 Started and ended
        logger.start("2");
        logger.poll();                      // No Completed Task
        logger.end("2");
        logger.poll();                      // No Completed Task
        logger.end("3");
        logger.poll();                      // 3 ended and 2 Ended
        logger.poll();
        logger.poll();
    }
}
