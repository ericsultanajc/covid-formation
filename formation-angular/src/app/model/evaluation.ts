export class Evaluation {
  id: number;
  version: number;
  comportemental: number;
  technique: number;
  commentaires: string;

  constructor(id?: number, version?: number, comportemental?: number, technique?: number, commentaires?: string) {
    this.id = id;
    this.version = version;
    this.comportemental = comportemental;
    this.technique = technique;
    this.commentaires = commentaires;
  }

}
