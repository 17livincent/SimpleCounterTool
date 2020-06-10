/*
    pitchcounter.cpp
    By Vincent Li <vincentl@asu.edu>
*/

#include "pitchcounter.h"

PitchCounter::PitchCounter() {
    count = 0;
}

void PitchCounter::add() {
    count++;
}

void PitchCounter::subtract() {
    if(count > 0) count--;
}

void PitchCounter::clear() {
    count = 0;
}

std::string PitchCounter::getCount() {
    std::string out = std::to_string(count);
    return out;
}