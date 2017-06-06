#include "precentage.h"
#include "jni.h"

JNIEXPORT jfloat JNICALL Java_ra210_12014_com_example_student_taskmanager_NativeClass_percentageCalculation
  (JNIEnv *env, jobject obj, jfloat done, jfloat sum)
  {
	return (jfloat) ((done/sum) * 100);
  }