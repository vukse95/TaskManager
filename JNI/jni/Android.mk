LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := libprecentage
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := precentage.c

include $(BUILD_SHARED_LIBRARY)