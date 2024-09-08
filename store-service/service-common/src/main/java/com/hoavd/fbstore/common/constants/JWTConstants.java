package com.hoavd.fbstore.common.constants;

public interface JWTConstants {
  String TOKEN_HEADER = "Authorization";

  String TOKEN_PREFIX = "Bearer ";

  String CLAIM_USER_ID = "userId";

  String CLAIM_ROLES = "roles";

  String CLAIM_EMAIL = "email";

  String CLAIM_SOCIAL_ID = "socialId";

  String CLAIM_PROVIDER = "provider";

  String ACTIVE_MODE = "prod";

  String LOCK_JWT = "LOCK_JWT";

  String BASIC_AUTH_PREFIX = "Basic ";
}
