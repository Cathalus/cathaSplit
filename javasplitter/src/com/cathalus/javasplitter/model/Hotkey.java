package com.cathalus.javasplitter.model;

/**
 * Created by Cathalus on 24/11/2015.
 */
public enum  Hotkey {
    DEFAULT {
        @Override
        public String toString() {
            return "DEFAULT";
        }
    },
    SPLIT {
        @Override
        public String toString() {
            return "Split";
        }
    },RESET {
        @Override
        public String toString() {
            return "Reset";
        }
    },NEXT {
        @Override
        public String toString() {
            return "Next";
        }
    },PREVIOUS {
        @Override
        public String toString() {
            return "Previous";
        }
    },START {
        @Override
        public String toString() {
            return "Start";
        }
    };

    public abstract String toString();
}
