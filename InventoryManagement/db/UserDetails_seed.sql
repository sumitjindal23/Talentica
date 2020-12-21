INSERT INTO `roles` (`name`) VALUES ('USER');
INSERT INTO `roles` (`name`) VALUES ('CREATOR');
INSERT INTO `roles` (`name`) VALUES ('EDITOR');
INSERT INTO `roles` (`name`) VALUES ('ADMIN');

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('patrick', '$2a$10$jdoWAKC9gHMFUADIhZt8mu16HIPLbxUtRVK814BVC4fJc7KbiGsEe', '1');
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('alex', '$2a$10$0p2Gh7VmjOryFQIhwCMmoO5VLRgMOPK3PZft1a4wfOPtIqC8DsErG', '1');
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('john', '$2a$10$EAlhIJM84rGLW5CORKLS3eUv/bICjD7se3Wf6DOmrw8MihCXWrWYa', '1');
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('namhm', '$2a$10$SMVuBEATicyH39vGyGCC6.5md7XeWlS9LqZ/ni3aSFDg90tNJBdgW', '1');
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('admin', '$2a$10$0r20zT2Bg0uRB6U0tbkK1.QqVT4cQf2XCSNgi/5paG17iSW1Ga64a', '1');


INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1); -- user patrick has role USER
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (2, 2); -- user alex has role CREATOR
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (3, 3); -- user john has role EDITOR
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (4, 2); -- user namhm has role CREATOR
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (4, 3); -- user namhm has role EDITOR
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (5, 4); -- user admin has role ADMIN