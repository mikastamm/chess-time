package chesstimeserver.networking;

import java.time.Duration;

import chesstimeserver.networking.servlets.UpdateFirebaseTokenServlet;
import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.constants.Constants;
import de.bytefish.fcmjava.http.options.IFcmClientSettings;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.model.topics.Topic;
import de.bytefish.fcmjava.requests.data.DataUnicastMessage;
import de.bytefish.fcmjava.requests.notification.NotificationPayload;
import de.bytefish.fcmjava.requests.notification.NotificationUnicastMessage;
import de.bytefish.fcmjava.requests.topic.TopicUnicastMessage;
import de.bytefish.fcmjava.responses.FcmMessageResponse;

public class FirebaseCommunicator {
	public static void sendTestFCM() {
		IFcmClientSettings settings = new FcmClientSettings();
		FcmClient client = new FcmClient(settings);
		 FcmMessageOptions options = FcmMessageOptions.builder()
				 .setTimeToLive(Duration.ofHours(1))
				 .build();
		 
	     NotificationPayload payload = NotificationPayload.builder()
	             .setBody("body")
	             .setTitle("Game found")
	             .build();
	    // FcmMessageResponse response = client.send(new DataUnicastMessage(options, UpdateFirebaseTokenServlet.userFirebaseToken, null));
		// FcmMessageResponse response = client.send(new NotificationUnicastMessage(options, UpdateFirebaseTokenServlet.userFirebaseToken, payload));
		// System.out.println("Sent FCM Succ:"+		 response.getNumberOfSuccess() + " Fail:"+response.getNumberOfFailure());
		 try {
			client.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Send a Firebase Message to a client
	public static void sendStringFCM(String message, String targetFirebaseId)
	{
		IFcmClientSettings settings = new FcmClientSettings();
		FcmClient client = new FcmClient(settings);
		 FcmMessageOptions options = FcmMessageOptions.builder()
				 .setTimeToLive(Duration.ofHours(1))
				 .build();
		 
	    
	     
	     
	     NotificationPayload payload = NotificationPayload.builder()
	             .setTag("TESTss")
	             .build();
		 FcmMessageResponse response = client.send(new NotificationUnicastMessage(options, UpdateFirebaseTokenServlet.userFirebaseToken, payload));
		 //FcmMessageResponse response = client.send(new DataUnicastMessage(options, UpdateFirebaseTokenServlet.userFirebaseToken, message));

		 System.out.println("Sent FCM Succ:"+		 response.getNumberOfSuccess() + " Fail:"+response.getNumberOfFailure());
		 
		 try {
				client.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
