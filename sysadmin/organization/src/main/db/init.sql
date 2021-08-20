DROP TABLE IF EXISTS `sentinel_degrade_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sentinel_degrade_rule` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `project_name` varchar(64) DEFAULT NULL COMMENT '服务名称',
  `resource` varchar(128) DEFAULT NULL COMMENT '资源名，即限流规则的作用对象',
  `count` int(5) DEFAULT NULL COMMENT '慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈值',
  `grade` int(5) DEFAULT NULL COMMENT '熔断策略，支持慢调用比例/异常比例/异常数策略',
  `time_window` int(5) DEFAULT NULL COMMENT '熔断时长，单位为 s',
  `min_request_amount` int(5) DEFAULT NULL COMMENT '熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断',
  `stat_interval_ms` int(8) DEFAULT NULL COMMENT '统计时长（单位为 ms），如 60*1000 代表分钟级',
  `slow_ratio_threshold` decimal(4,2) DEFAULT NULL COMMENT '慢调用比例阈值，仅慢调用比例模式有效',
  `status` int(1) DEFAULT NULL COMMENT '状态1=可用 0=不可用',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='sentinel熔断降级规则';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sentinel_degrade_rule`
--

LOCK TABLES `sentinel_degrade_rule` WRITE;
/*!40000 ALTER TABLE `sentinel_degrade_rule` DISABLE KEYS */;
INSERT INTO `sentinel_degrade_rule` VALUES (1,'gateway-admin','com.springcloud.sysadmin.organizationremote.remote.IUserServerRemote',1000,0,1,100,1000,1.00,1,'2021-07-12 09:39:45','2021-07-12 09:39:45',NULL,NULL);
/*!40000 ALTER TABLE `sentinel_degrade_rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sentinel_flow_control`
--

DROP TABLE IF EXISTS `sentinel_flow_control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sentinel_flow_control` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `project_name` varchar(64) DEFAULT NULL COMMENT '服务名称',
  `resource` varchar(128) DEFAULT NULL COMMENT '资源名，即限流规则的作用对象',
  `count` int(5) DEFAULT NULL COMMENT '限流阈值',
  `grade` int(5) DEFAULT NULL COMMENT '限流阈值类型（QPS 或并发线程数）',
  `limitApp` varchar(64) DEFAULT NULL COMMENT '流控针对的调用来源，若为 default 则不区分调用来源',
  `strategy` int(2) DEFAULT NULL COMMENT '调用关系限流策略',
  `controlBehavior` int(2) DEFAULT NULL COMMENT '流量控制效果（直接拒绝、Warm Up、匀速排队）',
  `status` int(1) DEFAULT NULL COMMENT '状态1=可用 0=不可用',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='sentinel流量控制规则';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sentinel_flow_control`
--

LOCK TABLES `sentinel_flow_control` WRITE;
/*!40000 ALTER TABLE `sentinel_flow_control` DISABLE KEYS */;
INSERT INTO `sentinel_flow_control` VALUES (1,'organization','com.springcloud.sysadmin.organizationremote.remote.IUserServerRemote',200,1,'default',0,0,1,'2021-06-22 23:42:05','2021-06-22 23:42:05',NULL,NULL),(2,'gateway-admin','com.springcloud.sysadmin.organizationremote.remote.IUserServerRemote',200,1,'default',0,0,1,'2021-06-22 23:42:05','2021-06-22 23:42:05',NULL,NULL);
/*!40000 ALTER TABLE `sentinel_flow_control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_authority`
--

DROP TABLE IF EXISTS `sys_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_authority` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(200) DEFAULT NULL COMMENT '权限名称',
  `description` varchar(500) DEFAULT NULL COMMENT '简介',
  `order_num` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `status` int(1) DEFAULT '1' COMMENT '状态 0=不可用 1=可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=293 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_authority`
--

LOCK TABLES `sys_authority` WRITE;
/*!40000 ALTER TABLE `sys_authority` DISABLE KEYS */;
INSERT INTO `sys_authority` VALUES (292,'读权限','宇宙最强-读',1,'2021-08-04 15:23:57','2021-08-04 15:23:57',NULL,NULL,1);
/*!40000 ALTER TABLE `sys_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_gateway_route`
--

DROP TABLE IF EXISTS `sys_gateway_route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_gateway_route` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `route_id` varchar(64) DEFAULT NULL COMMENT '路由id',
  `uri` varchar(128) DEFAULT NULL COMMENT '跳转地址',
  `predicate` varchar(256) DEFAULT NULL COMMENT '判定器',
  `filters` varchar(256) DEFAULT NULL COMMENT '拦截器',
  `status` int(1) DEFAULT '1' COMMENT '可用状态1=true, 0=false',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='网关路由';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_gateway_route`
--

LOCK TABLES `sys_gateway_route` WRITE;
/*!40000 ALTER TABLE `sys_gateway_route` DISABLE KEYS */;
INSERT INTO `sys_gateway_route` VALUES (1,'organization','lb://organization:8080','{\"Path\":\"/organization/**\",\"After\":\"2018-03-18T17:32:58.129+08:00[Asia/Shanghai]\"}','[{\"args\":{\"_genkey_0\":\"1\"},\"name\":\"StripPrefix\"}, {\"args\":{\"redis-rate-limiter.requestedTokens\":\"1\",\"key-resolver\":\"#{@apiKeyResolver}\",\"redis-rate-limiter.burstCapacity\":\"10\",\"redis-rate-limiter.replenishRate\":\"1\"},\"name\":\"RequestRateLimiter\"}]',1,NULL,'2021-06-04 15:21:52',NULL,'2021-06-04 15:21:52'),(2,'authorization','lb://authorization:8081','{\"Path\":\"/authorization/**\",\"After\":\"2018-03-18T17:32:58.129+08:00[Asia/Shanghai]\"}','[{\"args\":{\"_genkey_0\":\"1\"},\"name\":\"StripPrefix\"}, {\"args\":{\"redis-rate-limiter.requestedTokens\":\"1\",\"key-resolver\":\"#{@apiKeyResolver}\",\"redis-rate-limiter.burstCapacity\":\"10\",\"redis-rate-limiter.replenishRate\":\"1\"},\"name\":\"RequestRateLimiter\"}]',1,NULL,'2021-06-11 09:27:11',NULL,'2021-06-11 09:27:11'),(3,'gateway-admin','lb://gateway-admin:6445','{\"Path\":\"/gatewayAdmin/**\",\"After\":\"2018-03-18T17:32:58.129+08:00[Asia/Shanghai]\"}','[{\"args\":{\"_genkey_0\":\"1\"},\"name\":\"StripPrefix\"}, {\"args\":{\"redis-rate-limiter.requestedTokens\":\"1\",\"key-resolver\":\"#{@apiKeyResolver}\",\"redis-rate-limiter.burstCapacity\":\"10\",\"redis-rate-limiter.replenishRate\":\"1\"},\"name\":\"RequestRateLimiter\"}]',1,NULL,'2021-06-11 09:27:11',NULL,'2021-06-11 09:27:11');
/*!40000 ALTER TABLE `sys_gateway_route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_roles`
--

DROP TABLE IF EXISTS `sys_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_roles` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(200) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(500) DEFAULT NULL COMMENT '简介',
  `order_num` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  `status` int(1) DEFAULT '1' COMMENT '状态 0=不可用 1=可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=293 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_roles`
--

LOCK TABLES `sys_roles` WRITE;
/*!40000 ALTER TABLE `sys_roles` DISABLE KEYS */;
INSERT INTO `sys_roles` VALUES (292,'超级管理员','宇宙最强',1,'2021-08-04 15:21:26','2021-08-04 15:21:26',NULL,NULL,1);
/*!40000 ALTER TABLE `sys_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_expand_associated`
--

DROP TABLE IF EXISTS `sys_user_expand_associated`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_expand_associated` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `expand` int(5) DEFAULT NULL COMMENT '拓展类型 1=用户组',
  `application` varchar(1024) DEFAULT NULL COMMENT '动态拓展信息',
  `status` int(5) DEFAULT NULL COMMENT '可用状态：1=可用 2=不可用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户属性拓展表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_expand_associated`
--

LOCK TABLES `sys_user_expand_associated` WRITE;
/*!40000 ALTER TABLE `sys_user_expand_associated` DISABLE KEYS */;
INSERT INTO `sys_user_expand_associated` VALUES (1422837991945154561,1401568640119459842,1,'[{\"ids\":[\"292\"],\"userExpandEnum\":\"USER_ROLE\"},{\"ids\":[\"292\"],\"userExpandEnum\":\"USER_AUTHORITY\"}]',1,'2021-08-04 16:33:07','2021-08-04 16:37:50',NULL,NULL);
/*!40000 ALTER TABLE `sys_user_expand_associated` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_users`
--

DROP TABLE IF EXISTS `sys_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_users` (
  `id` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '用户密码密文',
  `name` varchar(200) DEFAULT NULL COMMENT '用户姓名',
  `authorities` varchar(200) DEFAULT NULL COMMENT 'oauth2权限',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '是否有效用户',
  `account_non_expired` tinyint(1) DEFAULT NULL COMMENT '账号是否未过期',
  `credentials_non_expired` tinyint(1) DEFAULT NULL COMMENT '密码是否未过期',
  `account_non_locked` tinyint(1) DEFAULT NULL COMMENT '是否未锁定',
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(100) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_users`
--


/*!40000 ALTER TABLE `sys_users` DISABLE KEYS */;
INSERT INTO `sys_users` VALUES (1401568640119459842,'liangjinquan17','$2a$10$t4f6dbkRjtADNtYgCQ6SJO8JNtQn0ybCl0TiQ0SiVHeF909ekxlXS','神烦阿噗','consumer',1,1,1,1,'2021-06-06 23:56:19','2021-06-06 23:55:43',NULL,NULL),(1403984502193426434,'liangjinquan18','$2a$10$cZ9zu5iRbDbMKiKlCpNzCe2sW49kxc./chPIhZikAZsT3hDtczsqS','神烦18','comsuner',1,1,1,1,'2021-06-13 15:56:05','2021-06-13 15:55:28',NULL,NULL),(1425000633824661505,'123','$2a$10$OdBDrG/V1eMv34qtXTzRTOacAzcHG4uWJ7eCiTdCaigl9Trpg5YWO','123','',1,1,1,1,'2021-08-10 15:46:41','2021-08-10 15:45:49',NULL,NULL),(1425001087941955585,'123','$2a$10$ESpfgXipsbJqGSEnjAK0/.fWVI2m6pMqu.MxiAflOZb7IV3goU7hW','12312','',1,1,1,1,'2021-08-10 15:48:29','2021-08-10 15:47:38',NULL,NULL),(1425003012477374466,'123','$2a$10$SS6q4dCYKNBX4LixKgshv.GifdT.bTX0w/QWRwZY8O.ozd9kprz1C','123','',1,1,1,1,'2021-08-10 15:56:08','2021-08-10 15:55:16',NULL,NULL),(1425003025181921281,'','$2a$10$FcfN6gpAhrpUXXKuuheUxeMIT5YQH.v15wpLt4HtxLwXtqD1SPTI2','','',1,1,1,1,'2021-08-10 15:56:11','2021-08-10 15:55:20',NULL,NULL),(1425003056723087361,'123','$2a$10$PPN4zz.tHdJdFgoavS/eLuCr6hn2Y5kQ775vnEbSDXe4MsbwbFwmS','123','',1,1,1,1,'2021-08-10 15:56:19','2021-08-10 15:55:27',NULL,NULL),(1425003077170319361,'123','$2a$10$kdjYRZnTD5SJ3ROAazmvY.HuoOftg.OigIu4tT3fFWNAQR8eC1PZK','123','',1,1,1,1,'2021-08-10 15:56:24','2021-08-10 15:55:32',NULL,NULL),(1425003382943469570,'123','$2a$10$6TFBnVpFgz9MCFMER2l91OJid9YhuEimgggnJaKrYLyCZ5nvDt6na','123','',1,1,1,1,'2021-08-10 15:57:36','2021-08-10 15:56:45',NULL,NULL),(1425006878556114945,'123','$2a$10$SyEOmEAHt46ws79OvCcXg.kjxKoPN1MlyHWSh0FBzKLi5vlFO9O62','123','',1,1,1,1,'2021-08-10 16:11:30','2021-08-10 16:10:38',NULL,NULL),(1425006936261349377,'123','$2a$10$FGaOhOOt.dr4zbX3cljBdezgV1xRQ0M7DHgHq7crlrSzOCsJr60Qy','12312312','',1,1,1,1,'2021-08-10 16:11:44','2021-08-10 16:10:52',NULL,NULL),(1425008156891893762,'shenfan1','$2a$10$d6ChXkqZ.HFDBbtCMCC5a.0n220iZKLqWKOvcJUu6fxm0.o75Ysfy','1231','',1,1,1,1,'2021-08-10 16:16:35','2021-08-10 16:15:43',NULL,NULL);
/*!40000 ALTER TABLE `sys_users` ENABLE KEYS */;
