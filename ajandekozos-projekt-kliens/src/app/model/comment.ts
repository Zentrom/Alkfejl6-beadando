import { User } from './user';

export class Comment {
    public id: number;

    constructor(
        private text: string,
        private timeStamp: Date,
        private user: User = null,
    ) {}

    public getId(): number {
        return this.id;
    }

    public getText(): string {
        return this.text;
    }

    public getTimeStamp(): Date {
        return this.timeStamp;
    }

    public getCommentAuthor(): User {
        return this.user;
    }

}