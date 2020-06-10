/*
    pitchcounter.h
    By Vincent Li <vincentl@asu.edu>
*/
#pragma ounce
#ifndef PITCHCOUNTER_PITCHCOUNTER_H
#define PITCHCOUNTER_PITCHCOUNTER_H
#include <string>

class PitchCounter {
public:
    PitchCounter();
    void add();
    void subtract();
    void clear();
    std::string getCount();
private:
    int count;
};

#endif //PITCHCOUNTER_PITCHCOUNTER_H