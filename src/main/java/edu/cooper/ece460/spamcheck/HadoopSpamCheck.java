package edu.cooper.ece460.spamcheck;

import java.io.*;
import java.util.*;

import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

// import javax.mail.*;
// import javax.mail.Flags.Flag;
// import javax.mail.internet.*;

// import com.sun.mail.imap.IMAPFolder;
// import com.sun.mail.imap.IMAPMessage;

public class HadoopSpamCheck {
	public static void main (String[] args) throws Exception {
		
		// FileSystem fileSystem = FileSystem.get(new Configuration());
   
		// // Check if the file already exists
		// Path path = new Path("fakespamcheck_in/subjects2.txt");
		// if (fileSystem.exists(path)) {
			// fileSystem.delete(path, true);
		// }

		// // Create a new file and write data to it.
		// FSDataOutputStream out = fileSystem.create(path);
		// //InputStream in = new BufferedInputStream(new FileInputStream(new File(path)));

		// //out.writeUTF("hello\nhello");
		// // Close all the file descripters
		// //in.close();

		
		
		// IMAPFolder folder = null;
        // Store store = null;
        // String subject = null;
        // Flag flag = null;
        // try 
        // {
          // Properties props = System.getProperties();
          // props.setProperty("mail.store.protocol", "imaps");

          // Session session = Session.getDefaultInstance(props, null);

          // store = session.getStore("imaps");
          // store.connect("imap.googlemail.com","flocc3@gmail.com", "xxx");

          // //folder = (IMAPFolder) store.getFolder("[Gmail]/Spam"); // This doesn't work for other email account
          // folder = (IMAPFolder) store.getFolder("inbox"); 


          // if(!folder.isOpen())
			// folder.open(Folder.READ_WRITE);
          // Message[] messages = folder.getMessages();
          // //System.out.println("No of Messages : " + folder.getMessageCount());
          // //System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
          // //System.out.println(messages.length);
          // for (int i=0; i < messages.length;i++) 
          // {
			
            // Message msg =  messages[i];
            // //System.out.println(msg.getMessageNumber());
            // //Object String;
            // //System.out.println(folder.getUID(msg)

            // subject = msg.getSubject();
			
			
            // //System.out.println("Subject: " + subject);
			// out.writeUTF("\"msg.getFrom()[0]\",");
			// out.writeUTF("\"subject\"\n");
            // // System.out.println("From: " + msg.getFrom()[0]);
           // // System.out.println("To: "+msg.getAllRecipients()[0]);
            // // System.out.println("Date: "+msg.getReceivedDate());
            // // System.out.println("Size: "+msg.getSize());
            // // System.out.println(msg.getFlags());
            // // System.out.println("Body: \n"+ msg.getContent());
            // // System.out.println(msg.getContentType());

          // }
        // }
        // finally 
        // {
          // if (folder != null && folder.isOpen()) { folder.close(true); }
          // if (store != null) { store.close(); }
        // }
	
		// out.close();
		// fileSystem.close();
		
		Configuration conf = new Configuration();
		Path inPath = new Path(args[0]);
		Path outPath = new Path(args[1]);

		Job job1 = new Job(conf, "HadoopSpamCheck");
		job1.setJarByClass(HadoopSpamCheck.class);
		job1.setMapperClass(SpamCheckMap.class);
		job1.setReducerClass(SpamCheckReduce.class);
		job1.setNumReduceTasks(12);
		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(Text.class);
		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);
		job1.setInputFormatClass(TextInputFormat.class);
		job1.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job1, inPath);
		FileOutputFormat.setOutputPath(job1, outPath);
		job1.waitForCompletion(true);
	}
}

