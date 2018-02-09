/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package org.pac4j.oauth.profile.sina;

import java.net.URI;
import java.util.Date;
import java.util.Locale;

import org.pac4j.core.profile.Gender;
import org.pac4j.oauth.profile.OAuth20Profile;

public class SinaWeboProfile extends OAuth20Profile {

	public String getEmail() {
		return (String) getAttribute(SinaWeiboProfileDefinition.EMAIL);
	}

	public String getFirstName() {
		return null;
	}

	public String getFamilyName() {
		return null;
	}

	public String getDisplayName() {
		return (String) getAttribute(SinaWeiboProfileDefinition.NAME);
	}

	public String getUsername() {
		return (String) getAttribute(SinaWeiboProfileDefinition.LOGIN);
	}

	public Gender getGender() {
		return Gender.UNSPECIFIED;
	}

	public Locale getLocale() {
		return null;
	}

	public URI getPictureUrl() {
		return (URI) getAttribute(SinaWeiboProfileDefinition.AVATAR_URL);
	}

	public URI getProfileUrl() {
		return (URI) getAttribute(SinaWeiboProfileDefinition.HTML_URL);
	}

	public String getLocation() {
		return (String) getAttribute(SinaWeiboProfileDefinition.LOCATION);
	}

	public String getCompany() {
		return (String) getAttribute(SinaWeiboProfileDefinition.COMPANY);
	}

	public Integer getFollowing() {
		return (Integer) getAttribute(SinaWeiboProfileDefinition.FOLLOWING);
	}

	public String getBlog() {
		return (String) getAttribute(SinaWeiboProfileDefinition.BLOG);
	}

	public Integer getPublicRepos() {
		return (Integer) getAttribute(SinaWeiboProfileDefinition.PUBLIC_REPOS);
	}

	public Integer getPublicGists() {
		return (Integer) getAttribute(SinaWeiboProfileDefinition.PUBLIC_GISTS);
	}

	public Integer getDiskUsage() {
		return (Integer) getAttribute(SinaWeiboProfileDefinition.DISK_USAGE);
	}

	public Integer getCollaborators() {
		return (Integer) getAttribute(SinaWeiboProfileDefinition.COLLABORATORS);
	}

	public Integer getOwnedPrivateRepos() {
		return (Integer) getAttribute(SinaWeiboProfileDefinition.OWNED_PRIVATE_REPOS);
	}

	public Integer getTotalPrivateRepos() {
		return (Integer) getAttribute(SinaWeiboProfileDefinition.TOTAL_PRIVATE_REPOS);
	}

	public Integer getPrivateGists() {
		return (Integer) getAttribute(SinaWeiboProfileDefinition.PRIVATE_GISTS);
	}

	public Integer getFollowers() {
		return (Integer) getAttribute(SinaWeiboProfileDefinition.FOLLOWERS);
	}

	public Date getCreatedAt() {
		return (Date) getAttribute(SinaWeiboProfileDefinition.CREATED_AT);
	}

	public String getType() {
		return (String) getAttribute(SinaWeiboProfileDefinition.TYPE);
	}

	public String getGravatarId() {
		return (String) getAttribute(SinaWeiboProfileDefinition.GRAVATAR_ID);
	}

	public String getUrl() {
		return (String) getAttribute(SinaWeiboProfileDefinition.URL);
	}

	public Boolean getHireable() {
		return (Boolean) getAttribute(SinaWeiboProfileDefinition.HIREABLE);
	}

	public String getBio() {
		return (String) getAttribute(SinaWeiboProfileDefinition.BIO);
	}

}
