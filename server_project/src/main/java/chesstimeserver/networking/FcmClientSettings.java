package chesstimeserver.networking;

import de.bytefish.fcmjava.constants.Constants;
import de.bytefish.fcmjava.http.options.IFcmClientSettings;

public class FcmClientSettings implements IFcmClientSettings{

	@Override
	public String getApiKey() {
		return "AAAAujatw-s:APA91bEO-VXKT7CZYAVrb1uQ3CZSLTM8Yll4WFKMxkjwSbbPHpjW_Dgf5HcFqTmLupE3ZrpKtQf-mppG7ifd0STKISAAyE1Xui1a4T3qSgpuyGCvpF4-3GN_pVr_VHnuNUbqIa2cT2VQ ";
	}

	@Override
	public String getFcmUrl() {
        return Constants.FCM_URL;	}

}
