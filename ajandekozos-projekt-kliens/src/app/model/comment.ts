import { User } from './user';

export class Comment {
    constructor(
        public id: number,
        public text: string,
        public timeStamp: Date,
        public authorName: string
    ) {}
}