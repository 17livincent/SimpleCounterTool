#include <jni.h>
#include <string>
#include "pitchcounter.cpp"

PitchCounter pc;

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_simplecountertool_MainActivity_stringFromJNI(JNIEnv* env, jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_simplecountertool_MainActivity_PitchCounter(JNIEnv *env, jobject) {
    pc = PitchCounter();

}extern "C"
JNIEXPORT void JNICALL
Java_com_example_simplecountertool_MainActivity_add(JNIEnv *env, jobject thiz) {
    pc.add();

}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_simplecountertool_MainActivity_subtract(JNIEnv *env, jobject thiz) {
    pc.subtract();

}
extern "C"
JNIEXPORT void JNICALL
Java_com_example_simplecountertool_MainActivity_clear(JNIEnv *env, jobject thiz) {
    pc.clear();

}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_simplecountertool_MainActivity_getCount(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF(pc.getCount().c_str());
}
