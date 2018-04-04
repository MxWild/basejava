package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

//    private static class ResumeComparator implements Comparator<Resume> {
//
//        @Override
//        public int compare(Resume o1, Resume o2) {
//            return o1.getUuid().compareTo(o2.getUuid());
//        }
//    }

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    protected void insertResume(Resume r, int index) {
        // идею нашел тут:
        // https://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        index = - index - 1;
        System.arraycopy(storage, index, storage,index + 1, size - index);
        storage[index] = r;
    }

    @Override
    protected void deleteResume(int index) {
        int lengthDelta = size - index - 1;
        // сдвигаем только в том случае, если  длина массива для копирования не отрицательная
        if (lengthDelta > 0) {
            // копирование массива из массива со следующего индекса, на текущий
            System.arraycopy(storage, index + 1, storage, index, lengthDelta);
        }
    }

    @Override
    protected Integer getKey(String uuid) {
        Resume searchKey = new Resume(uuid, "dummy");
        //searchKey.setUuid(uuid)  --  remove on HW4;
        // Методы поиска возвращают индекс найденного элемента массива.
        // Если элемент не найден, то возвращается отрицательное число, означающее индекс,
        // с которым элемент был бы вставлен в массив в заданном порядке, с обратным знаком.
       return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
//       return Arrays.binarySearch(storage, 0, size, searchKey);
    }

}
