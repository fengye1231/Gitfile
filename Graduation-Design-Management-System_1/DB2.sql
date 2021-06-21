
-- ----------------------------
-- View structure for `midcheckinfo`
-- ----------------------------
DROP VIEW IF EXISTS `midcheckinfo`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `midcheckinfo` AS select `mid_check`.`m_id` AS `m_id`,`mid_check`.`f_id` AS `f_id`,`mid_check`.`s_id` AS `s_id`,`mid_check`.`agree` AS `agree`,`myfile`.`f_name` AS `f_name`,`myfile`.`f_path` AS `f_path`,`myfile`.`upload_datetime` AS `upload_datetime`,`myfile`.`f_type` AS `f_type`,`teacher`.`t_id` AS `t_id`,`teacher`.`t_name` AS `t_name`,`student`.`s_name` AS `s_name`,`select_title`.`seltitl_state` AS `seltitl_state`,`title`.`titl_name` AS `titl_name`,`student`.`major` AS `major`,`student`.`batch` AS `batch` from (((((`mid_check` join `myfile` on((`mid_check`.`f_id` = `myfile`.`f_id`))) join `teacher`) join `student` on((`mid_check`.`s_id` = `student`.`s_id`))) join `select_title` on((`select_title`.`s_id` = `mid_check`.`s_id`))) join `title` on(((`select_title`.`titl_id` = `title`.`titl_id`) and (`title`.`t_id` = `teacher`.`t_id`)))) ;

-- ----------------------------
-- View structure for `openreportinfo`
-- ----------------------------
DROP VIEW IF EXISTS `openreportinfo`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `openreportinfo` AS select `open_report`.`r_id` AS `r_id`,`open_report`.`f_id` AS `f_id`,`open_report`.`s_id` AS `s_id`,`open_report`.`agree` AS `agree`,`myfile`.`f_name` AS `f_name`,`myfile`.`f_path` AS `f_path`,`myfile`.`upload_datetime` AS `upload_datetime`,`myfile`.`f_type` AS `f_type`,`teacher`.`t_id` AS `t_id`,`teacher`.`t_name` AS `t_name`,`student`.`s_name` AS `s_name`,`select_title`.`seltitl_state` AS `seltitl_state`,`title`.`titl_name` AS `titl_name`,`student`.`major` AS `major`,`student`.`batch` AS `batch` from (((((`open_report` join `myfile` on((`open_report`.`f_id` = `myfile`.`f_id`))) join `teacher`) join `student` on((`open_report`.`s_id` = `student`.`s_id`))) join `select_title` on((`select_title`.`s_id` = `open_report`.`s_id`))) join `title` on(((`select_title`.`titl_id` = `title`.`titl_id`) and (`title`.`t_id` = `teacher`.`t_id`)))) ;

-- ----------------------------
-- View structure for `projbookinfo`
-- ----------------------------
DROP VIEW IF EXISTS `projbookinfo`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `projbookinfo` AS select `proj_book`.`p_id` AS `p_id`,`proj_book`.`f_id` AS `f_id`,`proj_book`.`s_id` AS `s_id`,`proj_book`.`agree` AS `agree`,`myfile`.`f_name` AS `f_name`,`myfile`.`f_path` AS `f_path`,`myfile`.`upload_datetime` AS `upload_datetime`,`myfile`.`f_type` AS `f_type`,`teacher`.`t_id` AS `t_id`,`teacher`.`t_name` AS `t_name`,`student`.`s_name` AS `s_name`,`select_title`.`seltitl_state` AS `seltitl_state`,`title`.`titl_name` AS `titl_name`,`student`.`major` AS `major`,`student`.`batch` AS `batch` from (((((`proj_book` join `myfile` on((`proj_book`.`f_id` = `myfile`.`f_id`))) join `teacher`) join `student` on((`proj_book`.`s_id` = `student`.`s_id`))) join `select_title` on((`select_title`.`s_id` = `proj_book`.`s_id`))) join `title` on(((`select_title`.`titl_id` = `title`.`titl_id`) and (`title`.`t_id` = `teacher`.`t_id`)))) ;

-- ----------------------------
-- View structure for `thesisinfo`
-- ----------------------------
DROP VIEW IF EXISTS `thesisinfo`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `thesisinfo` AS select `thesis`.`thesis_id` AS `thesis_id`,`thesis`.`f_id` AS `f_id`,`thesis`.`s_id` AS `s_id`,`thesis`.`agree` AS `agree`,`thesis`.`titl_id` AS `titl_id`,`myfile`.`f_name` AS `f_name`,`myfile`.`f_path` AS `f_path`,`myfile`.`upload_datetime` AS `upload_datetime`,`myfile`.`f_type` AS `f_type`,`teacher`.`t_id` AS `t_id`,`teacher`.`t_name` AS `t_name`,`student`.`s_name` AS `s_name`,`select_title`.`seltitl_state` AS `seltitl_state`,`title`.`titl_name` AS `titl_name`,`student`.`major` AS `major`,`student`.`batch` AS `batch` from (((((`thesis` join `myfile` on((`thesis`.`f_id` = `myfile`.`f_id`))) join `teacher`) join `student` on((`thesis`.`s_id` = `student`.`s_id`))) join `select_title` on((`select_title`.`s_id` = `thesis`.`s_id`))) join `title` on(((`select_title`.`titl_id` = `title`.`titl_id`) and (`title`.`t_id` = `teacher`.`t_id`)))) ;

-- ----------------------------
-- View structure for `reviewscore`
-- ----------------------------
DROP VIEW IF EXISTS `reviewscore`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `reviewscore` AS select `thesisinfo`.`f_id` AS `f_id`,`review`.`review_score` AS `review_score`,`review`.`review_comments` AS `review_comments`,`review`.`reply_id` AS `reply_id`,`thesisinfo`.`agree` AS `agree`,`review`.`member_t_id` AS `member_t_id`,`thesisinfo`.`t_id` AS `t_id`,`thesisinfo`.`titl_name` AS `titl_name`,`thesisinfo`.`major` AS `major`,`thesisinfo`.`batch` AS `batch`,`thesisinfo`.`titl_id` AS `titl_id`,`thesisinfo`.`s_name` AS `s_name`,`thesisinfo`.`t_name` AS `t_name`,`thesisinfo`.`f_name` AS `f_name`,`teacher`.`t_name` AS `member_t_name`,`review`.`review_type` AS `review_type`,`reply_group`.`reply_leader` AS `reply_leader`,`thesisinfo`.`s_id` AS `s_id`,`select_title`.`reply_score` AS `reply_score` from ((((`thesisinfo` join `review` on((`review`.`s_id` = `thesisinfo`.`s_id`))) join `teacher` on((`review`.`member_t_id` = `teacher`.`t_id`))) join `reply_group` on((`review`.`reply_id` = `reply_group`.`reply_id`))) join `select_title` on(((`select_title`.`s_id` = `thesisinfo`.`s_id`) and (`select_title`.`titl_id` = `thesisinfo`.`titl_id`)))) ;

-- ----------------------------
-- View structure for `seltitleinfo`
-- ----------------------------
DROP VIEW IF EXISTS `seltitleinfo`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `seltitleinfo` AS select `title`.`titl_id` AS `titl_id`,`title`.`titl_name` AS `titl_name`,`title`.`t_id` AS `t_id`,`title`.`titl_source` AS `titl_source`,`title`.`titl_type` AS `titl_type`,`title`.`titl_describe` AS `titl_describe`,`title`.`titl_state` AS `titl_state`,`title`.`sel_state` AS `sel_state`,`select_title`.`seltitl_state` AS `seltitl_state`,`teacher`.`t_name` AS `t_name`,`select_title`.`s_id` AS `s_id`,`title`.`major` AS `major`,`student`.`s_name` AS `s_name` from (((`title` join `select_title` on((`select_title`.`titl_id` = `title`.`titl_id`))) join `teacher` on((`title`.`t_id` = `teacher`.`t_id`))) join `student` on((`select_title`.`s_id` = `student`.`s_id`))) ;

-- ----------------------------
-- View structure for `ss`
-- ----------------------------
DROP VIEW IF EXISTS `ss`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `ss` AS select `select_title`.`s_id` AS `s_id`,`select_title`.`titl_id` AS `titl_id`,`select_title`.`t_score` AS `t_score`,`select_title`.`t_comments` AS `t_comments`,`select_title`.`reply_score` AS `reply_score`,`select_title`.`reply_comments` AS `reply_comments`,`select_title`.`seltitl_state` AS `seltitl_state`,`student`.`s_name` AS `s_name`,`student`.`s_pwd` AS `s_pwd`,`student`.`s_class` AS `s_class`,`student`.`sex` AS `sex`,`student`.`age` AS `age`,`student`.`dept` AS `dept`,`student`.`major` AS `major`,`student`.`phone` AS `phone`,`student`.`email` AS `email`,`student`.`batch` AS `batch` from (`select_title` join `student` on((`student`.`s_id` = `select_title`.`s_id`))) ;

-- ----------------------------
-- View structure for `sss`
-- ----------------------------
DROP VIEW IF EXISTS `sss`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `sss` AS select `teacher`.`t_name` AS `t_name`,`title`.`titl_id` AS `titl_id`,`title`.`titl_name` AS `titl_name`,`title`.`t_id` AS `t_id`,`title`.`titl_source` AS `titl_source`,`title`.`titl_type` AS `titl_type`,`title`.`titl_describe` AS `titl_describe`,`title`.`titl_state` AS `titl_state`,`title`.`sel_state` AS `sel_state`,`title`.`major` AS `major` from (`title` join `teacher` on((`title`.`t_id` = `teacher`.`t_id`))) ;

-- ----------------------------
-- View structure for `studentscore`
-- ----------------------------
DROP VIEW IF EXISTS `studentscore`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `studentscore` AS select `title`.`titl_name` AS `titl_name`,`select_title`.`reply_comments` AS `reply_comments`,`select_title`.`reply_score` AS `reply_score`,`select_title`.`t_comments` AS `t_comments`,`select_title`.`t_score` AS `t_score`,`select_title`.`seltitl_state` AS `seltitl_state`,`select_title`.`titl_id` AS `titl_id`,`select_title`.`s_id` AS `s_id`,`teacher`.`t_id` AS `t_id`,`teacher`.`t_name` AS `t_name`,`student`.`s_name` AS `s_name`,`student`.`batch` AS `batch`,`student`.`major` AS `major` from (((`select_title` join `title` on((`title`.`titl_id` = `select_title`.`titl_id`))) join `teacher` on((`teacher`.`t_id` = `title`.`t_id`))) join `student` on((`select_title`.`s_id` = `student`.`s_id`))) ;

-- ----------------------------
-- View structure for `thesisattachmentinfo`
-- ----------------------------
DROP VIEW IF EXISTS `thesisattachmentinfo`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `thesisattachmentinfo` AS select `teacher`.`t_id` AS `t_id`,`teacher`.`t_name` AS `t_name`,`title`.`titl_name` AS `titl_name`,`select_title`.`seltitl_state` AS `seltitl_state`,`thesis_attachment`.`a_id` AS `a_id`,`thesis_attachment`.`f_id` AS `f_id`,`thesis_attachment`.`s_id` AS `s_id`,`student`.`s_name` AS `s_name`,`student`.`s_class` AS `s_class`,`student`.`major` AS `major`,`myfile`.`f_name` AS `f_name`,`myfile`.`f_path` AS `f_path`,`myfile`.`upload_datetime` AS `upload_datetime`,`myfile`.`f_type` AS `f_type`,`student`.`batch` AS `batch` from (((((`thesis_attachment` join `student` on((`student`.`s_id` = `thesis_attachment`.`s_id`))) join `myfile` on((`myfile`.`f_id` = `thesis_attachment`.`f_id`))) join `select_title` on((`thesis_attachment`.`s_id` = `select_title`.`s_id`))) join `title` on((`select_title`.`titl_id` = `title`.`titl_id`))) join `teacher` on((`title`.`t_id` = `teacher`.`t_id`))) ;

-- ----------------------------
-- View structure for `titleinfo`
-- ----------------------------
DROP VIEW IF EXISTS `titleinfo`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `titleinfo` AS select `teacher`.`t_name` AS `t_name`,`title`.`titl_id` AS `titl_id`,`title`.`titl_name` AS `titl_name`,`title`.`t_id` AS `t_id`,`title`.`titl_source` AS `titl_source`,`title`.`titl_type` AS `titl_type`,`title`.`titl_describe` AS `titl_describe`,`title`.`titl_state` AS `titl_state`,`title`.`sel_state` AS `sel_state`,`title`.`major` AS `major` from (`title` join `teacher` on((`teacher`.`t_id` = `title`.`t_id`))) ;
