DROP TABLE [dbo].[IsolationTests]
GO

CREATE TABLE [dbo].[IsolationTests]
(
    Id INT IDENTITY,
    COLl INT,
    COL2 INT,
    COL3 INT
)

GO


INSERT INTO [dbo].[IsolationTests]([COLl], [COL2], [COL3]) VALUES (1, 2, 3)
INSERT INTO [dbo].[IsolationTests]([COLl], [COL2], [COL3]) VALUES (1, 2, 3)
INSERT INTO [dbo].[IsolationTests]([COLl], [COL2], [COL3]) VALUES (1, 2, 3)
INSERT INTO [dbo].[IsolationTests]([COLl], [COL2], [COL3]) VALUES (1, 2, 3)
INSERT INTO [dbo].[IsolationTests]([COLl], [COL2], [COL3]) VALUES (1, 2, 3)
INSERT INTO [dbo].[IsolationTests]([COLl], [COL2], [COL3]) VALUES (1, 2, 3)
INSERT INTO [dbo].[IsolationTests]([COLl], [COL2], [COL3]) VALUES (1, 2, 3)

-- To use SNAPSHOT level isolation.
--ALTER DATABASE test  SET ALLOW_SNAPSHOT_ISOLATION ON