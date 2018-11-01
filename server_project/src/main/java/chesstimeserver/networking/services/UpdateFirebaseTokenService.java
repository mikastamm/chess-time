package chesstimeserver.networking.services;

import chesstimeserver.DatabaseContainer;

public class UpdateFirebaseTokenService {
	public void updateFirebaseToken(String passToken, String fbToken)
	{
		DatabaseContainer.getApplicationDatabase().updateFirebaseToken(passToken, fbToken);
	}
}
