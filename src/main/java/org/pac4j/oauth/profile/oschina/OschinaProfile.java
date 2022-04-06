package org.pac4j.oauth.profile.oschina;

import java.net.URI;

import org.pac4j.oauth.profile.OAuth20Profile;


/**
 * <p>This class is the user profile for Oschina (using OAuth protocol version 2) with appropriate getters.</p>
 * <p>It is returned by the {@link org.pac4j.oauth.client.OschinaClient}.</p>
 *
 * @author 		： <a href="https://github.com/hiwepy">wandl</a>
 */
public class OschinaProfile extends OAuth20Profile {

	@Override
    public String getDisplayName() {
        return (String) getAttribute(OschinaProfileDefinition.NAME);
    }

    @Override
    public String getUsername() {
        return (String) getAttribute(OschinaProfileDefinition.NAME);
    }

    @Override
    public String getFirstName() {
    	return (String) getAttribute(OschinaProfileDefinition.NAME);
    }

    @Override
    public URI getPictureUrl() {
        return (URI) getAttribute(OschinaProfileDefinition.AVATAR_URL);
    }

    @Override
    public URI getProfileUrl() {
        return (URI) getAttribute(OschinaProfileDefinition.URL);
    }

}
